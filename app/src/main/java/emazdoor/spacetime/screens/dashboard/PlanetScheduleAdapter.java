package emazdoor.spacetime.screens.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import emazdoor.spacetime.R;
import emazdoor.spacetime.screens.planet.PlanetModel;

/**
 * Created by sidhu on 10/22/2017.
 */

public class PlanetScheduleAdapter extends RecyclerView.Adapter<PlanetScheduleAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private boolean mDeparture;
    private List<PlanetModel> mItems = new ArrayList<>();

    public PlanetScheduleAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setFlights(List<PlanetModel> items,boolean departure) {
        mDeparture = departure;
        mItems.clear();
        notifyDataSetChanged();


        mItems.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public PlanetScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlanetScheduleAdapter.ViewHolder((FlightItemView)mInflater.inflate(R.layout.flight_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(PlanetScheduleAdapter.ViewHolder holder, int position) {
        PlanetModel planet = mItems.get(position);
        if(mDeparture)
        ((FlightItemView)holder.itemView).setFlight(mDeparture,planet.destination,planet.departure);
        else
            ((FlightItemView)holder.itemView).setFlight(mDeparture,planet.origin,planet.arrival);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(FlightItemView itemView) {
            super(itemView);
        }
    }

}
