package a7.n1mo.mobjav.a816.myapplication.DAO;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.PeliculaContainer;
import a7.n1mo.mobjav.a816.myapplication.Model.Serie;
import a7.n1mo.mobjav.a816.myapplication.Model.SerieContainer;
import a7.n1mo.mobjav.a816.myapplication.utils.HTTPConnectionManager;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;

/**
 * Created by pablo on 1/11/2016.
 */
public class DAOSeries {
    public void obtenerSeriesDeJson(Context context, ResultListener<List<Serie>> resultListenerController, String url){

        ReadSeriesFromJSONAsyncTask readSeriesFromJSONAsyncTask = new ReadSeriesFromJSONAsyncTask(resultListenerController, url);
        readSeriesFromJSONAsyncTask.execute();
    }

    //Obtengo varias series de json
    private class ReadSeriesFromJSONAsyncTask extends AsyncTask<String, Void,  List<Serie>> {


        private ResultListener <List<Serie>> resultListenerController;
        private String url;

        //agregar el atributo url crearlo en una clae tmdb helper y hacerlo con get sarasa
        public ReadSeriesFromJSONAsyncTask(ResultListener <List<Serie>> resultListenerController, String url){

            this.resultListenerController = resultListenerController;
            this.url = url;
        }

        @Override
        protected List<Serie> doInBackground(String... strings) {

            SerieContainer unaSerieContainer = null;

            try {
                //BUSQUEDA EN INTERNET
                HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
                InputStream newsJson = httpConnectionManager.getRequestStream(url);

                //PARSEO GSON
                BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(newsJson));
                Gson gson = new Gson();
                unaSerieContainer = gson.fromJson(bufferReaderIn, SerieContainer.class);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return unaSerieContainer.getSeries();
        }

        @Override
        protected void onPostExecute(List<Serie> unaListaDeSeries) {
            resultListenerController.finish(unaListaDeSeries);
        }
    }
}
