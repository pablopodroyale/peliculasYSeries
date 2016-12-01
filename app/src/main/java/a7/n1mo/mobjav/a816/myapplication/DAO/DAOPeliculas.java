package a7.n1mo.mobjav.a816.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.PeliculaContainer;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.utils.HTTPConnectionManager;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;

/**
 * Created by pablo on 1/11/2016.
 */
public class DAOPeliculas extends SQLiteOpenHelper {

    private static final String DATABASENAME = "PeliculasDBF";
    private static final Integer DATABASEVERSION = 1;

    //TABLA DE PELICULAS CON SUS CAMPOS.
    private static final String TABLEPELICULAS = "PeliculasFavoritas";
    private static final String TITLE = "original_title";
    private static final String DESCRIPTION = "overview";
    private static final String ID = "id";
    private static final String URLIMAGEN = "poster_path";
    private static final String PUNTAJE = "vote_average";

    public DAOPeliculas(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    //PARSEO EN JSON y obtengo una sola pelicula
    public void obtenerPeliculaDeJson(Context context, ResultListener<Pelicula> resultListenerController) {

        ReadFromJSONAsyncTask readFromJSONAsyncTask = new ReadFromJSONAsyncTask(resultListenerController);
        readFromJSONAsyncTask.execute();
    }



    //CREACION DE LA BASE DE DATOS POR PRIMERA VEZ.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String createTable = "CREATE TABLE " + TABLEPELICULAS + "("
                + ID + " TEXT PRIMARY KEY, "
                + TITLE + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + URLIMAGEN + " TEXT, "
                + PUNTAJE + " DOUBLE " + ")";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
    public boolean checkearFavorito(String idPelicula){
        SQLiteDatabase database = getReadableDatabase();
        String selectBusqueda = "SELECT * FROM " + TABLEPELICULAS
                + " WHERE " + ID + "= " + idPelicula;
        Cursor cursor = database.rawQuery(selectBusqueda, null);
        Integer count = cursor.getCount();
        return count > 0;

    }
    public void deletePeliculaFavoritos (String idPelicula){
        SQLiteDatabase database = getReadableDatabase();
        String selectBusqueda = "DELETE FROM " + TABLEPELICULAS
                + " WHERE " + ID + "=" + idPelicula;
        database.execSQL(selectBusqueda);
        database.close();
    }


    public void addPeliculasAFavoritos(Pelicula pelicula){
        //OBTENGO UNA CONEXION CON LA BASE DE DATOS.
        SQLiteDatabase dataBase = getWritableDatabase();
        ContentValues row = new ContentValues();

        //OBTENGO LOS DATOS DE LA PELICULA Y LOS CARGO EN EL ROW.
        row.put(ID, pelicula.getId());
        row.put(TITLE, pelicula.getTitulo());
        row.put(DESCRIPTION, pelicula.getDescripcion());
        row.put(URLIMAGEN, pelicula.getUrlImagen());
        row.put(PUNTAJE, pelicula.getPuntaje());

        dataBase.insert(TABLEPELICULAS, null, row);

        //ANTES DE SALIR DEL METODO TENGO QUE CERRAR LA CONEXION CON LA BASE DE DATOS.
        dataBase.close();
    }
    //METODO PARA CONSULTAR A MI TABLA DE PELICULAS QUE ME DE UNA PELICULA ENVASE A SU "ID".
    public Pelicula getPelicula(String id){

        //CON ESTE METODO OBTENGO UNA CONEXION CON LA BASE DE DATOS.
        SQLiteDatabase database = getReadableDatabase();
        String selectBusqueda = "SELECT * FROM " + TABLEPELICULAS
                                + " WHERE " + ID + "= " + id;

        //CON ESTE METODO OBTENGO UN CURSOR QUE APUNTA AL COMIENZO DE LA TABLA DEL BUSCADOR.
        Cursor cursor = database.rawQuery(selectBusqueda, null);

        //CON ESTE METODO LO POSICIONO SOBRE LA PRIMERA FILA.
        cursor.moveToFirst();

        Pelicula pelicula = new Pelicula();

        //LEI DE LA TABLA EL VALOR "ID" Y LO ALMACENE.
        String unId = cursor.getString(cursor.getColumnIndex(ID));
        pelicula.setId(unId);
        pelicula.setTitulo(cursor.getString(cursor.getColumnIndex(TITLE)));
        pelicula.setDescripcion(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        pelicula.setUrlImagen(cursor.getString(cursor.getColumnIndex(URLIMAGEN)));
        pelicula.setPuntaje(cursor.getDouble(cursor.getColumnIndex(PUNTAJE)));

        //ANTES DE SALIR DEL METODO TENGO QUE CERRAR LA CONEXION CON LA BASE DE DATOS Y LIBERAR EL CURSOR.
        cursor.close();
        database.close();

        return   pelicula;
    }

    public List<Pelicula> getAllPeliculas(){
        SQLiteDatabase database = getReadableDatabase();

        String selectBusqueda = "SELECT * FROM " + TABLEPELICULAS;
        Cursor cursor = database.rawQuery(selectBusqueda, null);
        List<Pelicula> miListaDePeliculasFavoritas = new ArrayList<>();

        while (cursor.moveToNext()){
            //LEI DE LA TABLA EL VALOR "ID" Y LO ALMACENE.
            Pelicula unaPelicula = new Pelicula();

            String unId = cursor.getString(cursor.getColumnIndex(ID));
            unaPelicula.setId(unId);
            unaPelicula.setTitulo(cursor.getString(cursor.getColumnIndex(TITLE)));
            unaPelicula.setDescripcion(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            unaPelicula.setUrlImagen(cursor.getString(cursor.getColumnIndex(URLIMAGEN)));
            unaPelicula.setPuntaje(cursor.getDouble(cursor.getColumnIndex(PUNTAJE)));

            miListaDePeliculasFavoritas.add(unaPelicula);
        }
        cursor.close();
        database.close();

        return miListaDePeliculasFavoritas;
    }


    //Obtengo varias peliculas de json
    private class ReadFromJSONAsyncTask extends AsyncTask<String, Void, Pelicula> {


        private ResultListener<Pelicula> resultListenerController;

        //agregar el atributo url crearlo en una clae tmdb helper y hacerlo con get sarasa
        public ReadFromJSONAsyncTask(ResultListener<Pelicula> resultListenerController) {

            this.resultListenerController = resultListenerController;
        }

        @Override
        protected Pelicula doInBackground(String... strings) {

            Pelicula unaPelicula = null;

            try {
                //BUSQUEDA EN INTERNET
                HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
                InputStream newsJson = httpConnectionManager.getRequestStream("https://api.myjson.com/bins/z5cg");

                //PARSEO GSON
                BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(newsJson));
                Gson gson = new Gson();
                unaPelicula = gson.fromJson(bufferReaderIn, Pelicula.class);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return unaPelicula;
        }

        @Override
        protected void onPostExecute(Pelicula unaPelicula) {
            resultListenerController.finish(unaPelicula);
        }
    }

    public void obtenerPeliculasDeJson(Context context, ResultListener<List<Pelicula>> resultListenerController, String url) {

        ReadPeliculasFromJSONAsyncTask readPeliculasFromJSONAsyncTask = new ReadPeliculasFromJSONAsyncTask(resultListenerController, url);
        readPeliculasFromJSONAsyncTask.execute();
    }

    //Obtengo varias peliculas de json
    private class ReadPeliculasFromJSONAsyncTask extends AsyncTask<String, Void, List<Pelicula>> {


        private ResultListener<List<Pelicula>> resultListenerController;
        private String url;

        //agregar el atributo url crearlo en una clae tmdb helper y hacerlo con get sarasa
        public ReadPeliculasFromJSONAsyncTask(ResultListener<List<Pelicula>> resultListenerController, String url) {

            this.resultListenerController = resultListenerController;
            this.url = url;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {

            PeliculaContainer unContainerDePelis = null;

            try {
                //BUSQUEDA EN INTERNET
                HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
                InputStream newsJson = httpConnectionManager.getRequestStream(url);

                //PARSEO GSON
                BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(newsJson));
                Gson gson = new Gson();
                unContainerDePelis = gson.fromJson(bufferReaderIn, PeliculaContainer.class);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return unContainerDePelis.getPeliculas();
        }

        @Override
        protected void onPostExecute(List<Pelicula> unaListaDePeliculas) {
            resultListenerController.finish(unaListaDePeliculas);
        }
    }
}
