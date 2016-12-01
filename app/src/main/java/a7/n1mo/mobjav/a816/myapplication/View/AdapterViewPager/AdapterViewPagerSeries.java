package a7.n1mo.mobjav.a816.myapplication.View.AdapterViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler.FragmentRecyclerSeries;

/**
 * Created by pablo on 11/11/2016.
 */
public class AdapterViewPagerSeries extends FragmentStatePagerAdapter {
    private List<FragmentRecyclerSeries> miListaDeFragments = new ArrayList<>();

    public AdapterViewPagerSeries(FragmentManager fm) {
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

    public void agregarUnFragment(FragmentRecyclerSeries unFragmentRecyclerSeries){
        miListaDeFragments.add(unFragmentRecyclerSeries);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return this.miListaDeFragments.get(position).getTituloSerie();
    }
}
