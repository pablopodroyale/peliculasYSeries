
package a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.zip.Inflater;

import a7.n1mo.mobjav.a816.myapplication.Controller.ControllerPelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.View.AdapterRecycler.AdapterRecyclerPeliculas;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;


/**
 * Created by pablo on 23/11/2016.
 */

public class FragmentRecyclerFavooritosPeliculas extends Fragment {
    private View view;
    private RecyclerView unRecyclerView;

    private AdapterRecyclerPeliculas adapterRecyclerPeliculas;



    @Nullable
    @Override
    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_recycler_favoritos_peliculas, container, false);
            ControllerPelicula controllerPelicula=new ControllerPelicula();
            List <Pelicula> listaFavoritos= controllerPelicula.traerPeliculasFavoritasDeSql(getContext());
        unRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPeliculasfavoritas);
        unRecyclerView.setHasFixedSize(true);
        unRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       adapterRecyclerPeliculas= new AdapterRecyclerPeliculas(getContext(),listaFavoritos,AdapterRecyclerPeliculas.CELDA_PARA_FAVORITOS);
        unRecyclerView.setAdapter(adapterRecyclerPeliculas);






        return view;

    }
}

