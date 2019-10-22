package com.example.demojsonactivity.model;

import java.io.Serializable;

public class Country implements Serializable {
    private String countryName;
    private String shortName;
    private String imageurl;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getImageurl(){
        return imageurl;
    }
    public void setImageurl(String imageurl){
        this.imageurl= imageurl;
    }
}
