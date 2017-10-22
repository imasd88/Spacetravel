package emazdoor.spacetime.screens.planet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import emazdoor.spacetime.Constants;
import emazdoor.spacetime.R;
import emazdoor.spacetime.Util;
import emazdoor.spacetime.screens.dashboard.DashboardActivity;
import emazdoor.spacetime.network.FlightsTimeRequest;
import emazdoor.spacetime.helper.ProgressWheel;
import emazdoor.spacetime.network.SpaceTravelRestClient;

public class PlanetSelectorActivity extends AppCompatActivity implements View.OnClickListener
        ,SpaceTravelRestClient.Flight ,
        PlanetSelectorAdapter.Listener{

    private ImageButton btnClose;
    private TextView title;
    private RecyclerView rvPlanets;
    private PlanetSelectorAdapter mAdapter;
    private List<PlanetModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_selector);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnClose = toolbar.findViewById(R.id.btnClose);
        title = toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);
        rvPlanets = (RecyclerView) findViewById(R.id.rvPlanetSelector);
        setFont();
        btnClose.setOnClickListener(this);
        rvPlanets.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PlanetSelectorAdapter(this,this);
        rvPlanets.setAdapter(mAdapter);
        ProgressWheel.show(this,"Loading...");
        loadFlights();


    }


    private void setFont(){
        title.setTypeface(Util.getFont(this, Constants.FONT_MUSEO_500));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnClose:
                finish();
                break;
        }
    }


    private void loadFlights() {
        RequestParams params = new RequestParams();
        params.put("authkey","1a0f423312e24ef9246afed7d2a18a06");
        params.put("action","flights");
        FlightsTimeRequest.getFlights(params,this);
    }


    @Override
    public void flightsLoadedSuccessfully(JSONArray jsonArray) {
        ProgressWheel.hide();
        //Toast.makeText(this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<PlanetModel>>(){}.getType();
        list = (List<PlanetModel>) gson.fromJson(jsonArray.toString(), listType);



        mAdapter.setFlights(sortDistinctPlanets(list));
    }

    private List<PlanetModel> sortDistinctPlanets(List<PlanetModel> list){
        final List<PlanetModel> sortedList = new ArrayList<>();
        if(list.isEmpty())
            return sortedList;
        for(PlanetModel planet:list){
            if(!containsPlanet(planet.origin,sortedList))
                sortedList.add(planet);
        }


        Collections.sort(sortedList, new Comparator<PlanetModel>() {
            @Override
            public int compare(final PlanetModel object1, final PlanetModel object2) {
                return object1.origin.compareTo(object2.origin);
            }
        });

        return sortedList;
    }

    private boolean containsPlanet(String origin,List<PlanetModel> list){

        for(PlanetModel planetModel:list){
            if(planetModel.origin.equals(origin))
                return true;
        }
        return false;
    }

    @Override
    public void failedToLoadFlights(String msg) {
        ProgressWheel.hide();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlanetSelection(final PlanetModel selectedPlanet) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mAdapter.setCheckedPlanet(selectedPlanet.origin);
            }
        });

        Intent i = new Intent();
        i.setClass(this, DashboardActivity.class);
        i.putExtra("title",selectedPlanet.origin);
        i.putExtra("Flights",(Serializable) list);
        startActivity(i);
    }
}
