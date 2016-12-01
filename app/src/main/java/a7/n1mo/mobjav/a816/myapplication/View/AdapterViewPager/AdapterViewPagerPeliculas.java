package a7.n1mo.mobjav.a816.myapplication.View.AdapterViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler.FragmentRecyclerPeliculas;

/**
 * Created by taral on 11/2/2016.
 */

public class AdapterViewPagerPeliculas extends FragmentStatePagerAdapter {

    private List<FragmentRecyclerPeliculas>miListaDeFragments = new ArrayList<>();

    public AdapterViewPagerPeliculas(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return miListaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return miListaDeFragments.size();
    }

    public void agregarUnFragment(FragmentRecyclerPeliculas unFragmentRecyclerPeliculas){
        miListaDeFragments.add(unFragmentRecyclerPeliculas);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return this.miListaDeFragments.get(position).getTitulo();
    }
}
