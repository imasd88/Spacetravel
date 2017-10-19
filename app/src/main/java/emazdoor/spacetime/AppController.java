package emazdoor.spacetime;

import android.app.Application;

/**
 * Created by sidhu on 10/18/2017.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class
            .getSimpleName();


    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }


}