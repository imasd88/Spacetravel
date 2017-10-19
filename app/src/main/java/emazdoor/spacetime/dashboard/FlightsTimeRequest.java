package emazdoor.spacetime.dashboard;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import emazdoor.spacetime.network.SpaceTravelRestClient;

/**
 * Created by sidhu on 10/19/2017.
 */

public class FlightsTimeRequest {
    public static void getFlights(RequestParams params, final SpaceTravelRestClient.Flight listener){
        SpaceTravelRestClient.get("",params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                            listener.flightsLoadedSuccessfully(response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try{
                    if(response.has("status"))
                    {
                        if(response.getString("status").equals("success"))
                            listener.flightsLoadedSuccessfully(new JSONArray());
                        else
                            listener.failedToLoadFlights(response.getString("message"));

                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                listener.failedToLoadFlights(throwable.getMessage());
            }

        });
    }

}
