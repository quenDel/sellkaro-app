
package com.sellkaro.model.city;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityListReponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("cities")
    @Expose
    private List<City> cities = new ArrayList<City>();
    public final static Creator<CityListReponse> CREATOR = new Creator<CityListReponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CityListReponse createFromParcel(Parcel in) {
            return new CityListReponse(in);
        }

        public CityListReponse[] newArray(int size) {
            return (new CityListReponse[size]);
        }

    }
    ;

    protected CityListReponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.cities, (City.class.getClassLoader()));
    }

    public CityListReponse() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(cities);
    }

    public int describeContents() {
        return  0;
    }

}
