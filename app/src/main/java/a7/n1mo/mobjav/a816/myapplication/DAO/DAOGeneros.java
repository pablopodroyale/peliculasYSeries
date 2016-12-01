package a7.n1mo.mobjav.a816.myapplication.DAO;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.Model.Genero;
import a7.n1mo.mobjav.a816.myapplication.Model.GeneroContainer;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.PeliculaContainer;
import a7.n1mo.mobjav.a816.myapplication.utils.HTTPConnectionManager;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;
import a7.n1mo.mobjav.a816.myapplication.utils.TMDBHelper;

/**
 * Created by pablo on 18/11/2016.
 */
public class DAOGeneros {
    public void obtenerGenerosDeJson(Context context, ResultListener<List<Genero>> resultListenerController) {

        ReadGenerosFromJSONAsyncTask readGenerosFromJSONAsyncTask = new ReadGenerosFromJSONAsyncTask(resultListenerController);
        readGenerosFromJSONAsyncTask.execute();
    }

    //Obtengo varias peliculas de json
    private class ReadGenerosFromJSONAsyncTask extends AsyncTask<String, Void,  List<Genero>> {


        private ResultListener <List<Genero>> resultListenerController;

        //agregar el atributo url crearlo en una clae tmdb helper y hacerlo con get sarasa
        public ReadGenerosFromJSONAsyncTask(ResultListener <List<Genero>> resultListenerController){

            this.resultListenerController = resultListenerController;
        }

        @Override
        protected List<Genero> doInBackground(String... strings) {

            GeneroContainer unGeneroContainer = null;

            try {
                //BUSQUEDA EN INTERNET
                HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
                InputStream newsJson = httpConnectionManager.getRequestStream(TMDBHelper.getAllGenres(TMDBHelper.language_SPANISH));

                //PARSEO GSON
                BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(newsJson));
                Gson gson = new Gson();
                unGeneroContainer = gson.fromJson(bufferReaderIn, GeneroContainer.class);



            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return unGeneroContainer.getListaDeGeneros();
        }

        @Override
        protected void onPostExecute(List<Genero> unaListaDeGeneros) {
            resultListenerController.finish(unaListaDeGeneros);
        }
    }



}
