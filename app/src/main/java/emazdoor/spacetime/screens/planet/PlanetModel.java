package emazdoor.spacetime.screens.planet;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sidhu on 10/21/2017.
 */

public class PlanetModel implements Serializable{

    @SerializedName("origin")
    public String origin;
    @SerializedName("destination")
    public String destination;
    @SerializedName("departure")
    public String departure;
    @SerializedName("arrival")
    public String arrival;
    @SerializedName("tripDuration")
    public int tripDuration;

    public boolean isChecked;

/*    protected PlanetModel(Parcel in) {
    }

    public static final Creator<PlanetModel> CREATOR = new Creator<PlanetModel>() {
        @Override
        public PlanetModel createFromParcel(Parcel in) {
            return new PlanetModel(in);
        }

        @Override
        public PlanetModel[] newArray(int size) {
            return new PlanetModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(origin);
        parcel.writeString(destination);
        parcel.writeString(arrival);
        parcel.writeString(departure);
        parcel.writeInt(tripDuration);
    }*/
}
