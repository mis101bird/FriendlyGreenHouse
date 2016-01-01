package com.example.friendlygreenhouse.application.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ser on 2015/12/30.
 */
public class CallAPIhelper {

    RequestQueue requeseQueue;

    public CallAPIhelper() {
        AppConfig appSetting=new AppConfig();
        requeseQueue=appSetting.getRequestQueue();
    }

    public void sendChangeFlowerOrder(Context context){


        if (ifInternetOpen(context)) {

            StringRequest simpleRequest = new StringRequest(Request.Method.GET, "url",
                    new ResponseAndContext<String>(context) {

                        @Override
                        public void onResponse(String response) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("請檢察wifi認證或請重開App", error.getMessage());
                        }
                    }
            );
            requeseQueue.add(simpleRequest);
        } else {
            Log.i("Internet Error", "user phone didn't open internet.");
        }

    }
public static HashMap<String,String> getDictionaryFlowers(Context context){
    HashMap<String,String> DictionaryResult=new HashMap<>();
    // HashMap<String(花名),String(URL)>
    //實作網路請求Server端關於養花大百科的資料
    return DictionaryResult;
}

    public boolean ifInternetOpen(Context context) {

        final ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() || mobile.isAvailable()) {
            return true;
        } else {
            Toast.makeText(context, "請開起手機網路才能使用本App", Toast.LENGTH_LONG).show();
            return false;
        }


    }
    abstract class ResponseAndContext<T> implements Response.Listener<T> {
        Context context=null;
        public ResponseAndContext(Context context) {
            this.context=context;
        }

    }
}
