package emazdoor.spacetime.screens.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import emazdoor.spacetime.R;
import emazdoor.spacetime.screens.planet.PlanetModel;

/**
 * Created by sidhu on 10/22/2017.
 */

public class PlanetScheduleFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SELECTED_TAB = "section_number";
    private static final String ARG_FLIGHTS = "scheduled_flights";
    private static final String ARG_CURRENT_PLANET = "current_selected_planet";
    private  int selectedTab;
    private String selectedPlanet;
    private List<PlanetModel> mFlights;
    private RecyclerView rvFlight;
    private PlanetScheduleAdapter mAdapter;
    public PlanetScheduleFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlanetScheduleFragment newInstance(int sectionNumber, List<PlanetModel> flights,String currentPlanet) {
        PlanetScheduleFragment fragment = new PlanetScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SELECTED_TAB, sectionNumber);
        args.putString(ARG_CURRENT_PLANET, currentPlanet);
        args.putSerializable(ARG_FLIGHTS,(Serializable) flights);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.planet_schedule_fragment, container, false);
        selectedTab = getArguments().getInt(ARG_SELECTED_TAB);
        selectedPlanet = getArguments().getString(ARG_CURRENT_PLANET);
        mFlights =(List<PlanetModel>) getArguments().getSerializable(ARG_FLIGHTS);
        rvFlight = (RecyclerView) rootView.findViewById(R.id.rvFlights);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFlight.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PlanetScheduleAdapter(getContext());
        rvFlight.setAdapter(mAdapter);
        boolean isDeparture = selectedTab==1?true:false;
        mAdapter.setFlights(filterFlights(mFlights,isDeparture),isDeparture);
    }

    private List<PlanetModel> filterFlights(List<PlanetModel> list,boolean isDeparture){
        final List<PlanetModel> filteredList = new ArrayList<>();

        if(list.isEmpty())
            return filteredList;
        for(PlanetModel planet:list){
            if(isDeparture) {
                if (planet.origin.contentEquals(selectedPlanet))
                    filteredList.add(planet);
            }else{
                if (planet.destination.contentEquals(selectedPlanet))
                    filteredList.add(planet);
            }
        }
        return filteredList;

    }
}


