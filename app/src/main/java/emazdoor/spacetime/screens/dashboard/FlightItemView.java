package emazdoor.spacetime.screens.dashboard;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import emazdoor.spacetime.R;
import emazdoor.spacetime.screens.planet.PlanetModel;

/**
 * Created by sidhu on 10/22/2017.
 */

public class FlightItemView extends LinearLayout {

    TextView destinationPlanet,tvFlightTime,tvToFrom;
    PlanetModel mPlanet;
    public FlightItemView(Context context) {
        super(context);
    }

    public FlightItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlightItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.destinationPlanet = (TextView) findViewById(R.id.tvToDestination);
        this.tvFlightTime = (TextView) findViewById(R.id.tvFlightTime);
        this.tvToFrom = (TextView) findViewById(R.id.tvToFrom);
    }

    public void setFlight(boolean mDeparture,String planet,String time){
        this.destinationPlanet.setText(planet);
        this.tvFlightTime.setText(formatTime(time));
        this.tvToFrom.setText(mDeparture?"to":"from");
    }


    /**
     * convert give time to specified format time string
     * @param time
     * @return
     */
    private String formatTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            Date date = sdf.parse(time);
            return new SimpleDateFormat("hh:mm").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


}
