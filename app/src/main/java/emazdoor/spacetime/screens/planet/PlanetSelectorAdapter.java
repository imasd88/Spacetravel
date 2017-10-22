package emazdoor.spacetime.screens.planet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import emazdoor.spacetime.R;

/**
 * Created by sidhu on 10/21/2017.
 */

public class PlanetSelectorAdapter extends RecyclerView.Adapter<PlanetSelectorAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private PlanetSelectorAdapter.Listener mListener;
    private List<PlanetModel> mItems = new ArrayList<>();

    public PlanetSelectorAdapter(Context context, PlanetSelectorAdapter.Listener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    public void setFlights(List<PlanetModel> items) {
        mItems.clear();
        notifyDataSetChanged();


        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void setCheckedPlanet(String selectedPlanet){
        for(PlanetModel planetModel:mItems){
            if(planetModel.origin.equals(selectedPlanet))
                planetModel.isChecked=true;
            else
                planetModel.isChecked=false;
        }
        notifyDataSetChanged();
    }

    @Override
    public PlanetSelectorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlanetSelectorAdapter.ViewHolder((PlanetSelectorItemView)mInflater.inflate(R.layout.planet_selector, parent, false));
    }

    @Override
    public void onBindViewHolder(PlanetSelectorAdapter.ViewHolder holder, int position) {
        PlanetModel planet = mItems.get(position);
        ((PlanetSelectorItemView)holder.itemView).setPlanet(planet, mListener);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(PlanetSelectorItemView itemView) {
            super(itemView);
        }
    }

    public interface Listener {
        void onPlanetSelection(PlanetModel selectedPlanet);
    }
}
