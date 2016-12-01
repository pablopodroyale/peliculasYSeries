package a7.n1mo.mobjav.a816.myapplication.Controller;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.DAO.DAOPeliculas;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;
import a7.n1mo.mobjav.a816.myapplication.utils.TMDBHelper;

/**
 * Created by pablo on 1/11/2016.
 */
public class ControllerPelicula {
    private ResultListener<Pelicula> resultListenerView;
    private Context context;

   /* public ControllerPelicula(Context context, ResultListener<List<Pelicula>> resultListener, String popularMovies){
        this.context = context;
    }

    public ControllerPelicula(ResultListener<Pelicula> resultListenerView, Context context) {
        this.resultListenerView = resultListenerView;
        this.context = context;
    }*/


   /* public void obtenerPeliculaDeJson(final ResultListener<Pelicula> resultListenerView) {
        //PEDIRLE AL DAO UNA Pelicula
        DAOPeliculas daoPeliculas = new DAOPeliculas();
        ResultListenerController resultListenerController = new ResultListenerController();
        this.resultListenerView = resultListenerView;
        daoPeliculas.obtenerPeliculaDeJson(context, resultListenerController);


    }
    public void obtenerPeliculaDeJson(final ResultListener<Pelicula> listenerPelicula) {
        //PEDIRLE AL DAO UNA Pelicula
        DAOPeliculas daoPeliculas = new DAOPeliculas();
        daoPeliculas.obtenerPeliculaDeJson(context, new ResultListener<Pelicula>() {
            @Override
            public void finish(Pelicula resultado) {
                listenerPelicula.finish(resultado);
            }
        });
    }*/

    public void obtenerPeliculasPopularesDeJson(Context context, final ResultListener <List<Pelicula>> listenerDePeliculas){
        //PEDIRLE AL DAO UNA Pelicula
        DAOPeliculas daoPeliculas = new DAOPeliculas(context);
        daoPeliculas.obtenerPeliculasDeJson(context, new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listenerDePeliculas.finish(resultado);
            }
        }, TMDBHelper.getPopularMovies(TMDBHelper.language_SPANISH,1));
    }
    public void obtenerPeliculasPorGeneroDeJson(Context context, final ResultListener <List<Pelicula>> listenerDePeliculas,Integer idGenero){
        //PEDIRLE AL DAO UNA Pelicula.
        DAOPeliculas daoPeliculas = new DAOPeliculas(context);
        daoPeliculas.obtenerPeliculasDeJson(context, new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listenerDePeliculas.finish(resultado);
            }
        }, TMDBHelper.getMoviesByGenre(idGenero.toString(),1,TMDBHelper.language_SPANISH));
    }


    /* public ArrayList<Pelicula> obtenerListaDePeliculas(Context unContext){
         DAOPeliculas DAOPeliculas = new DAOPeliculas();
         ArrayList<Pelicula> listaDePeliculas = DAOPeliculas.obtenerListaDePeliculas(unContext);
         return listaDePeliculas;
     }*/

    public void agregarPeliculaAFavoritos(Pelicula pelicula, Context context){
        DAOPeliculas daoPeliculas =new DAOPeliculas(context);
        if(daoPeliculas.checkearFavorito(pelicula.getId())) {
            daoPeliculas.deletePeliculaFavoritos(pelicula.getId());
            Toast.makeText(context, "Se borro la pelicula de favoritos",Toast.LENGTH_SHORT).show();

        }else {
            daoPeliculas.addPeliculasAFavoritos(pelicula);
            Toast.makeText(context, "Se agrego la pelicula a favoritos",Toast.LENGTH_SHORT).show();

        }
    }
    public List<Pelicula> traerPeliculasFavoritasDeSql(Context context){

        DAOPeliculas daoPeliculas=new DAOPeliculas(context);
        return daoPeliculas.getAllPeliculas();

    }

    public boolean checkFavorito(String idPelicula, Context context){
        DAOPeliculas daoPeliculas =new DAOPeliculas(context);
        return  daoPeliculas.checkearFavorito(idPelicula);

    }
}