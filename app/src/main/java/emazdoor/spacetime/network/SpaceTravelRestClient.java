package emazdoor.spacetime.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import emazdoor.spacetime.Constants;

/**
 * Created by sidhu on 10/19/2017.
 */

public class SpaceTravelRestClient {


    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constants.BASE_URL + relativeUrl;
    }

    public interface User{
        void loggedInSuccessfully();
        void failedToLogin(String msg);
    }

    public interface Flight{
        void flightsLoadedSuccessfully(JSONArray jsonArray);
        void failedToLoadFlights(String msg);
    }
}
