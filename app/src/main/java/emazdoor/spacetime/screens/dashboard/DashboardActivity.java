package emazdoor.spacetime.screens.dashboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import emazdoor.spacetime.Constants;
import emazdoor.spacetime.R;
import emazdoor.spacetime.Util;
import emazdoor.spacetime.screens.planet.PlanetModel;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener,TabLayout.OnTabSelectedListener {

    private PlanetPagerAdapter mPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout mTabs;
    private String currentPlanet;
    private TextView title, planets;
    private List<PlanetModel> mFlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = toolbar.findViewById(R.id.title);
        planets = toolbar.findViewById(R.id.planets);
        setSupportActionBar(toolbar);
        currentPlanet = getIntent().getStringExtra("title");
        title.setText(currentPlanet);
        title.setTypeface(Util.getFont(this, Constants.FONT_MUSEO_500));
        mFlights = (List<PlanetModel>) getIntent().getSerializableExtra("Flights");
        mTabs = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mPagerAdapter = new PlanetPagerAdapter(getSupportFragmentManager(),mFlights,currentPlanet);
        mViewPager.setAdapter(mPagerAdapter);
        mTabs.setupWithViewPager(mViewPager);

        setupCustomTabs();
        setListeners();
    }

    private void setListeners() {
        planets.setOnClickListener(this);
        mTabs.addOnTabSelectedListener(this);
    }

    private void setupCustomTabs() {
        ((TextView) mTabs.getTabAt(0).setCustomView(R.layout.tab_item_view).getCustomView().findViewById(R.id.tvTab)).setText("DEPARTURE");
        ((TextView) mTabs.getTabAt(1).setCustomView(R.layout.tab_item_view).getCustomView().findViewById(R.id.tvTab)).setText("ARRIVALS");
        ((TextView) mTabs.getTabAt(0).getCustomView().findViewById(R.id.tvTab)).setTextColor(ContextCompat.getColor(DashboardActivity.this, R.color.planetSelected));
        mTabs.getTabAt(0).getCustomView().setBackgroundResource(R.drawable.tab_selected);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.planets:
                finish();
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        ((TextView) tab.getCustomView().findViewById(R.id.tvTab)).setTextColor(ContextCompat.getColor(DashboardActivity.this, R.color.planetSelected));
        tab.getCustomView().setBackgroundResource(R.drawable.tab_selected);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        ((TextView) tab.getCustomView().findViewById(R.id.tvTab)).setTextColor(ContextCompat.getColor(DashboardActivity.this, android.R.color.white));
        tab.getCustomView().setBackgroundResource(R.drawable.tab_unselected);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
