package a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.Controller.ControllerPelicula;
import a7.n1mo.mobjav.a816.myapplication.Controller.ControllerSerie;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.Serie;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.View.AdapterRecycler.AdapterRecyclerPeliculas;
import a7.n1mo.mobjav.a816.myapplication.View.AdapterRecycler.AdapterRecyclerSeries;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;

/**
 * Created by pablo on 22/11/2016.
 */
public class FragmentRecyclerPantallaInicial extends Fragment {
    private View view;
    private List<Pelicula> unaListaDePeliculas;
    private AdapterRecyclerPeliculas adapterRecyclerPeliculas;
    private AdapterRecyclerSeries adapterRecyclerSeries;
    private SeleccionadorDePeliculasPantallaInicial seleccionadorDePeliculas;
    private String tituloPelicula;
    private RecyclerView unRecyclerRandomDePelis;
    private RecyclerView unRecyclerRandomDeSeries;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater miInflador, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = miInflador.inflate(R.layout.fragment_pantalla_inicial, container, false);

        unRecyclerRandomDePelis = (RecyclerView) view.findViewById(R.id.recyclerViewPeliculasRandom);
        unRecyclerRandomDePelis.setHasFixedSize(true);
        unRecyclerRandomDePelis.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ControllerPelicula controllerPelicula = new ControllerPelicula();
        controllerPelicula.obtenerPeliculasPopularesDeJson(getActivity(), new ResultListener<List<Pelicula>>(){

            @Override
            public void finish(List<Pelicula> resultado){
                adapterRecyclerPeliculas = new AdapterRecyclerPeliculas(getActivity(), resultado,new EscuchadorDePeliculas(), AdapterRecyclerPeliculas.CELDA_PARA_INICIAL);
                unRecyclerRandomDePelis.setAdapter(adapterRecyclerPeliculas);
            }
        });


        unRecyclerRandomDeSeries = (RecyclerView) view.findViewById(R.id.recyclerViewSeriesRandom);
        unRecyclerRandomDeSeries.setHasFixedSize(true);
        unRecyclerRandomDeSeries.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ControllerSerie controllerSerie = new ControllerSerie();
        controllerSerie.obtenerSeriesPopularesDeJson(getActivity(), new ResultListener<List<Serie>>(){

            @Override
            public void finish(List<Serie> resultado){
                adapterRecyclerSeries = new AdapterRecyclerSeries(getActivity(), resultado,new EscuchadorDeSeries(), AdapterRecyclerSeries.CELDA_PARA_INICIAL);
                unRecyclerRandomDeSeries.setAdapter(adapterRecyclerSeries);
            }
        });

        return view;
    }
    public interface SeleccionadorDePeliculasPantallaInicial{
        public void peliculaSeleccionada(Pelicula unapelicula);
        public void serieSeleccionada(Serie unaSerie);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        seleccionadorDePeliculas = (SeleccionadorDePeliculasPantallaInicial)activity;
    }

    public class EscuchadorDePeliculas implements View.OnClickListener{

        @Override
        public void onClick(View view){
            Integer posicion = unRecyclerRandomDePelis.getChildAdapterPosition(view);
            List<Pelicula> miListaDePelis = adapterRecyclerPeliculas.getListaDePeliculas();
            Pelicula unaPelicula = miListaDePelis.get(posicion);
            seleccionadorDePeliculas.peliculaSeleccionada(unaPelicula);
        }
    }
    public class EscuchadorDeSeries implements View.OnClickListener{

        @Override
        public void onClick(View view){
            Integer posicion = unRecyclerRandomDeSeries.getChildAdapterPosition(view);
            List<Serie> miListaDeSeries = adapterRecyclerSeries.getListaDeSeries();
            Serie unaSerie = miListaDeSeries.get(posicion);
            seleccionadorDePeliculas.serieSeleccionada(unaSerie);
        }
    }
}

/*
     * Created by pablo on 22/11/2016.

public class FragmentRecyclerPantallaInicialSerie extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater miInflador, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = miInflador.inflate(R.layout.fragment_pantalla_inicial, container, false);

        final RecyclerView unRecyclerRandom = (RecyclerView) view.findViewById(R.id.recyclerViewSeriesRandom);
        unRecyclerRandom.setHasFixedSize(true);
        unRecyclerRandom.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ControllerSerie controllerSerie = new ControllerSerie();
        controllerSerie.obtenerSeriesPorGeneroDeJson(getActivity(), new ResultListener<List<Serie>>(){

            @Override
            public void finish(List<Serie> resultado){
                AdapterRecyclerSeries adapterRecyclerSeries = new AdapterRecyclerSeries(getActivity(), resultado);
                unRecyclerRandom.setAdapter(adapterRecyclerSeries);

            }
        });

        return view;
    }
}*/