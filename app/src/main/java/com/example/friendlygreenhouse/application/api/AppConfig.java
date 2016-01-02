package com.example.friendlygreenhouse.application.api;

import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ser on 2016/1/1.
 */
public class AppConfig extends Application{
    private static RequestQueue RequestQueue=null;
    private static AppConfig mInstance;
    private static String setting;
    private static String airHum;
    private static String soilHum;
    private static String species;
    private static String temp;
    private static String i_fan;
    private static String i_photo;
    private static String i_water;
    private static String userID;

    public static String getI_fan() {
        return i_fan;
    }

    public static void setI_fan(String i_fan) {
        AppConfig.i_fan = i_fan;
    }

    public static String getI_photo() {
        return i_photo;
    }

    public static void setI_photo(String i_photo) {
        AppConfig.i_photo = i_photo;
    }

    public static String getI_water() {
        return i_water;
    }

    public static void setI_water(String i_water) {
        AppConfig.i_water = i_water;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        AppConfig.userID = userID;
    }

    public static String getSetting() {
        return setting;
    }

    public static void setSetting(String setting) {
        AppConfig.setting = setting;
    }

    public static String getAirHum() {
        return airHum;
    }

    public static void setAirHum(String airHum) {
        AppConfig.airHum = airHum;
    }

    public static String getSoilHum() {
        return soilHum;
    }

    public static void setSoilHum(String soilHum) {
        AppConfig.soilHum = soilHum;
    }

    public static String getTemp() {
        return temp;
    }

    public static void setTemp(String temp) {
        AppConfig.temp = temp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static AppConfig getInstance() {
        if(mInstance == null ) {mInstance = new AppConfig();}
        return mInstance;
    }

    public static String getSpecies() {
        return species;
    }

    public static void setSpecies(String species) {
        AppConfig.species = species;
    }

    public RequestQueue getRequestQueue() {
        Log.i("requestQueue", "Into GET requestQueue");
        if (RequestQueue == null) {
            RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return RequestQueue;
    }
}
