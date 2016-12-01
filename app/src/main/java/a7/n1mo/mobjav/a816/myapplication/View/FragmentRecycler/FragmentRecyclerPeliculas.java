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

import a7.n1mo.mobjav.a816.myapplication.Controller.ControllerPelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.Genero;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.View.AdapterRecycler.AdapterRecyclerPeliculas;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;

/**
 * Created by taral on 11/2/2016.
 */

public class FragmentRecyclerPeliculas extends Fragment {

    private View view;
    private RecyclerView unRecyclerView;
    private List<Pelicula> unaListaDePeliculas;
    private AdapterRecyclerPeliculas adapterRecyclerPeliculas;
    private SeleccionadorDePeliculas seleccionadorDePeliculas;
    private String tituloPelicula;

    public static FragmentRecyclerPeliculas dameUnFragment(Genero genero){
        FragmentRecyclerPeliculas fragmentRecyclerPeliculas=new FragmentRecyclerPeliculas();
        fragmentRecyclerPeliculas.setTituloPelicula(genero.getNombreGenero());
        Bundle bundle = new Bundle();
        bundle.putString("nombreGenero", genero.getNombreGenero());
        bundle.putInt("idGenero", genero.getId());
        fragmentRecyclerPeliculas.setArguments(bundle);
        return fragmentRecyclerPeliculas;

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
        unaListaDePeliculas = new ArrayList<>();

        ControllerPelicula controllerPelicula = new ControllerPelicula();
        controllerPelicula.obtenerPeliculasPorGeneroDeJson(getContext(), new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado){
                adapterRecyclerPeliculas.setListaDePeliculas(resultado);
                adapterRecyclerPeliculas.notifyDataSetChanged();
            }
        },idGeneroRecibido);

        /*Para probar si estamos parseando bien películas, intentamos agregar una película a la lista
        parseada desde internet con el método nuevo.*/

        adapterRecyclerPeliculas = new AdapterRecyclerPeliculas(getContext(), unaListaDePeliculas, new EscuchadorDePeliculas(), AdapterRecyclerPeliculas.CELDA_PARA_GRID);
        unRecyclerView.setAdapter(adapterRecyclerPeliculas);

        return view;
    }

    public interface SeleccionadorDePeliculas{
        public void peliculaSeleccionada(Pelicula unapelicula);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        seleccionadorDePeliculas = (SeleccionadorDePeliculas)activity;
    }

    public class EscuchadorDePeliculas implements View.OnClickListener{

        @Override
        public void onClick(View view){
            Integer posicion = unRecyclerView.getChildAdapterPosition(view);
            List<Pelicula> miListaDePelis = adapterRecyclerPeliculas.getListaDePeliculas();
            Pelicula unaPelicula = miListaDePelis.get(posicion);
            seleccionadorDePeliculas.peliculaSeleccionada(unaPelicula);
        }
    }

    public String getTitulo() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String titulo) {
        this.tituloPelicula = titulo;
    }
}
