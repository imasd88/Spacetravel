package emazdoor.spacetime.screens.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import emazdoor.spacetime.screens.planet.PlanetModel;

/**
 * Created by sidhu on 10/21/2017.
 */

public class PlanetPagerAdapter extends FragmentPagerAdapter {

    List<PlanetModel> mFlights;
    private String mCurrentPlanet;

    public PlanetPagerAdapter(FragmentManager fm, List<PlanetModel> flights,String currentPlanet) {
        super(fm);
        this.mFlights = flights;
        this.mCurrentPlanet = currentPlanet;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlanetScheduleFragment.newInstance(position + 1,mFlights,mCurrentPlanet);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Departure";
            case 1:
                return "Arrival";
        }
        return null;
    }
}