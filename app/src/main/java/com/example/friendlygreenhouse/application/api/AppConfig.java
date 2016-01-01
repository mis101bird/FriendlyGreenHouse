package com.example.friendlygreenhouse.application.api;

import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ser on 2016/1/1.
 */
public class AppConfig extends Application{

    AppConfig mInstance=null;
    RequestQueue RequestQueue=null;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public AppConfig getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        Log.i("requestQueue", "Into GET requestQueue");
        if (RequestQueue == null) {
            RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return RequestQueue;
    }
}
