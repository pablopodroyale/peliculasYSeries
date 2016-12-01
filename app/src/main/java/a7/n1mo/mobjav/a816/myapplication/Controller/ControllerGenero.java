package a7.n1mo.mobjav.a816.myapplication.Controller;

import android.content.Context;

import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.DAO.DAOGeneros;
import a7.n1mo.mobjav.a816.myapplication.DAO.DAOPeliculas;
import a7.n1mo.mobjav.a816.myapplication.Model.Genero;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;

/**
 * Created by pablo on 18/11/2016.
 */
public class ControllerGenero {
    private ResultListener<Genero> resultListenerView;
    private Context context;

    public void obtenerGenerosDeJson(Context context, final ResultListener <List<Genero>> listenerDeGeneros){
        //PEDIRLE AL DAO UNA Pelicula
        DAOGeneros daoGeneros = new DAOGeneros();
        daoGeneros.obtenerGenerosDeJson(context, new ResultListener<List<Genero>>() {
            @Override
            public void finish(List<Genero> resultado) {
                listenerDeGeneros.finish(resultado);
            }
        });
    }

}
