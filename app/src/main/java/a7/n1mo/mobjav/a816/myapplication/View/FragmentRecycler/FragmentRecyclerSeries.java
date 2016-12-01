package a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.Controller.ControllerSerie;
import a7.n1mo.mobjav.a816.myapplication.Model.Genero;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.Serie;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.View.AdapterRecycler.AdapterRecyclerSeries;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;

/**
 * Created by taral on 11/2/2016.
 */

public class FragmentRecyclerSeries extends Fragment {
    private View view;

    private RecyclerView unRecyclerView;
    private ArrayList<Serie> unaListaDeSeries;
    private AdapterRecyclerSeries adapterRecyclerSeries;
    private SeleccionadorDeSeries seleccionadorDeSeries;
    private String tituloSerie;

    public static FragmentRecyclerSeries dameUnFragment(Genero genero) {
        FragmentRecyclerSeries fragmentRecyclerSeries = new FragmentRecyclerSeries();
        fragmentRecyclerSeries.setTituloSerie(genero.getNombreGenero());
        Bundle bundle = new Bundle();
        bundle.putString("nombreGenero", genero.getNombreGenero());
        bundle.putInt("idGenero", genero.getId());
        fragmentRecyclerSeries.setArguments(bundle);
        return fragmentRecyclerSeries   ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater miInflador, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = miInflador.inflate(R.layout.fragment_viewpager_principal, container, false);

        Bundle unBundle=getArguments();
        Integer idGeneroRecibido= unBundle.getInt("idGenero");

        unRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerPrincipal);
        unRecyclerView.setHasFixedSize(true);

        unRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        unaListaDeSeries = new ArrayList<>();

        ControllerSerie controllerSerie = new ControllerSerie();
        controllerSerie.obtenerSeriesPorGeneroDeJson(getContext(), new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                adapterRecyclerSeries.setListaDeSeries(resultado);
                adapterRecyclerSeries.notifyDataSetChanged();
            }
        },idGeneroRecibido);

        /*Para probar si estamos parseando bien series, intentamos agregar una serie a la lista
        parseada desde internet con el método nuevo.*/

        adapterRecyclerSeries = new AdapterRecyclerSeries(getContext(), unaListaDeSeries, new EscuchadorDeSeries(), AdapterRecyclerSeries.CELDA_PARA_GRID);
        unRecyclerView.setAdapter(adapterRecyclerSeries);

        return view;
    }

    public interface SeleccionadorDeSeries {
        public void serieSeleccionada(Serie unaSerie);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        seleccionadorDeSeries = (SeleccionadorDeSeries)activity;
    }

    public class EscuchadorDeSeries implements View.OnClickListener{

        @Override
        public void onClick(View view){
            Integer posicion = unRecyclerView.getChildAdapterPosition(view);
            List<Serie> miListaDeSeries = adapterRecyclerSeries.getListaDeSeries();
            Serie unaSerie = miListaDeSeries.get(posicion);
            seleccionadorDeSeries.serieSeleccionada(unaSerie);
        }
    }

    public String getTituloSerie() {
        return tituloSerie;
    }

    public void setTituloSerie(String tituloSerie) {
        this.tituloSerie = tituloSerie;
    }
}
