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
import a7.n1mo.mobjav.a816.myapplication.View.AdapterViewPager.AdapterViewPagerSeries;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler.FragmentRecyclerSeries;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewPagerSeries extends Fragment {

    private AdapterViewPagerSeries adapterViewPager;

    public FragmentViewPagerSeries(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater miInflador, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = miInflador.inflate(R.layout.fragment_view_pager_series, container, false);
        final ViewPager unViewPager = (ViewPager)view.findViewById(R.id.viewPagerIDSeries);

        adapterViewPager = new AdapterViewPagerSeries(getActivity().getSupportFragmentManager());

        ControllerGenero controllerGenero = new ControllerGenero();
        controllerGenero.obtenerGenerosDeJson(getActivity(), new ResultListener<List<Genero>>() {
            @Override
            public void finish(List<Genero> resultado) {
                for (Genero unGenero : resultado){
                    adapterViewPager.agregarUnFragment(FragmentRecyclerSeries.dameUnFragment(unGenero));
                }
                adapterViewPager.notifyDataSetChanged();
            }
        });

        //adapterViewPager.agregarUnFragment(new FragmentRecyclerSeries());
        unViewPager.setAdapter(adapterViewPager);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabsSeries);
        tabLayout.setupWithViewPager(unViewPager);

        return view;
    }
}
