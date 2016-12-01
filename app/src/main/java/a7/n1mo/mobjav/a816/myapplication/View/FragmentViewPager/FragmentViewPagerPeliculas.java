package a7.n1mo.mobjav.a816.myapplication.View.FragmentViewPager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.Controller.ControllerGenero;
import a7.n1mo.mobjav.a816.myapplication.Model.Genero;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.View.AdapterViewPager.AdapterViewPagerPeliculas;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler.FragmentRecyclerPeliculas;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;
import a7.n1mo.mobjav.a816.myapplication.utils.TMDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewPagerPeliculas extends Fragment {

    private  AdapterViewPagerPeliculas adapterViewPager;


    public FragmentViewPagerPeliculas(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater miInflador, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = miInflador.inflate(R.layout.fragment_view_pager_peliculas, container, false);
        ViewPager unViewPager = (ViewPager)view.findViewById(R.id.viewPagerIDPeliculas);

        adapterViewPager = new AdapterViewPagerPeliculas(getActivity().getSupportFragmentManager());

        ControllerGenero controllerGenero = new ControllerGenero();
        controllerGenero.obtenerGenerosDeJson(getActivity(), new ResultListener<List<Genero>>() {
            @Override
            public void finish(List<Genero> resultado){
                for (Genero unGenero : resultado){
                    adapterViewPager.agregarUnFragment(FragmentRecyclerPeliculas.dameUnFragment(unGenero));
                }
                adapterViewPager.notifyDataSetChanged();
            }
        });

        //adapterViewPager.agregarUnFragment(new FragmentRecyclerPeliculas());
        unViewPager.setAdapter(adapterViewPager);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabsPeliculas);
        tabLayout.setupWithViewPager(unViewPager);

        return view;
    }
}
