
package com.sellkaro.model.city;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("selected")
    @Expose
    private Boolean selected = false;

    public final static Creator<City> CREATOR = new Creator<City>() {


        @SuppressWarnings({
            "unchecked"
        })
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        public City[] newArray(int size) {
            return (new City[size]);
        }

    }
    ;

    protected City(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.countryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.selected = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(countryId);
        dest.writeValue(name);
        dest.writeValue(createdAt);
        dest.writeValue(active);
        dest.writeValue(selected);
    }

    public int describeContents() {
        return  0;
    }

}
