package emazdoor.spacetime.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import emazdoor.spacetime.helper.ProgressWheel;
import emazdoor.spacetime.network.SpaceTravelRestClient;

/**
 * Created by sidhu on 10/19/2017.
 */

public class DashboardActivity extends AppCompatActivity implements SpaceTravelRestClient.Flight {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.dashboard);

        ProgressWheel.show(this,"Loading...");
        loadFlights();
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
        Toast.makeText(this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failedToLoadFlights(String msg) {
        ProgressWheel.hide();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
