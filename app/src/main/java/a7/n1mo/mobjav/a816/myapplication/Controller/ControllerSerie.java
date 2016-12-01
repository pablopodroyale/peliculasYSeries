package a7.n1mo.mobjav.a816.myapplication.Controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.DAO.DAOPeliculas;
import a7.n1mo.mobjav.a816.myapplication.DAO.DAOSeries;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.Serie;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;
import a7.n1mo.mobjav.a816.myapplication.utils.TMDBHelper;

/**
 * Created by pablo on 11/11/2016.
 */
public class ControllerSerie {
    private ResultListener<Serie> resultListenerView;
    private Context context;

    /* public ControllerSerie(Context context) {
        this.context = context;
    }

    public ControllerSerie(ResultListener<Serie> resultListenerView, Context context) {
        this.resultListenerView = resultListenerView;
        this.context = context;
    }
    public void obtenerPeliculasDeJson(Context context, final ResultListener <List<Serie>> listenerDeSeries){
        //PEDIRLE AL DAO UNA Pelicula
        DAOSeries daoSeries = new DAOSeries();
        daoSeries.obtenerSeriesDeJson(context, new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listenerDeSeries.finish(resultado);
            }
        });
    }*/

    public void obtenerSeriesPopularesDeJson(Context context, final ResultListener<List<Serie>>listenerDeSerie){
        DAOSeries daoSeries = new DAOSeries();
        daoSeries.obtenerSeriesDeJson(context, new ResultListener<List<Serie>>(){
            @Override
            public void finish(List<Serie> resultado){
             listenerDeSerie.finish(resultado);
            }
        }, TMDBHelper.getTVPopular(TMDBHelper.language_SPANISH, 1));
    }

    public void obtenerSeriesPorGeneroDeJson(Context context, final ResultListener <List<Serie>> listenerDeSeries,Integer idGenero){
        //PEDIRLE AL DAO UNA Serie.
        DAOSeries daoSeries = new DAOSeries();
        daoSeries.obtenerSeriesDeJson(context, new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listenerDeSeries.finish(resultado);
            }
        }, TMDBHelper.getTVByGenre(idGenero.toString(),1,TMDBHelper.language_SPANISH));
    }
}