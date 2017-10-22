package emazdoor.spacetime.screens.planet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import emazdoor.spacetime.R;

/**
 * Created by sidhu on 10/21/2017.
 */

public class PlanetSelectorItemView extends LinearLayout {

    CheckBox plantName;
    PlanetSelectorAdapter.Listener mListener;
    PlanetModel mPlanet;
    public PlanetSelectorItemView(Context context) {
        super(context);
    }

    public PlanetSelectorItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlanetSelectorItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.plantName = (CheckBox) findViewById(R.id.cbPlanet);
    }

    public void setPlanet(PlanetModel planet, PlanetSelectorAdapter.Listener listener){
        this.mPlanet = planet;
        this.mListener = listener;
        this.plantName.setText(planet.origin);
        this.plantName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;

                mListener.onPlanetSelection(mPlanet);
            }
        });
        if(planet.isChecked) {
                this.plantName.setChecked(true);
            } else {
                this.plantName.setChecked(false);
            }
    }


}
