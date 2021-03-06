package com.example.friendlygreenhouse.application.api;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.friendlygreenhouse.application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ser on 2015/12/30.
 */
public class CallAPIhelper {

    RequestQueue requeseQueue;
    String host="http://140.121.197.132:8080";

    public CallAPIhelper() {
        AppConfig appSetting=AppConfig.getInstance();
        requeseQueue=appSetting.getRequestQueue();
    }

    public void sendChangeFlowerOrder(Context context,String SpeciesID,String userID){


        if (ifInternetOpen(context)) {

            JsonObjectRequest simpleRequest = new JsonObjectRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/speciesSet/setSpecies?SpeciesID="+SpeciesID+"&userID="+userID,
                    new ResponseAndContext<JSONObject>(context) {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("sendChangeFlowerOrder", response.toString());
                            try {
                                if(((String)response.get("status")).equals("OK")){
                                    Log.i("sendChangeFlowerOrder", "Change Successful");
                                    Toast.makeText(context,"成功更改花種狀態",Toast.LENGTH_LONG);
                                }
                            }catch (JSONException e) {
                                Log.i("sendChangeFlowerOrder", e.getMessage());
                            }
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
    public void getUserID(Context context,String account,String password) {

        if (ifInternetOpen(context)) {

            StringRequest simpleRequest = new StringRequest(Request.Method.GET, host + "/FriendlyGreenHouseBackEnd/loginValidation/VerificateAccount?account=" + account+"&password="+password,
                    new ResponseAndContext<String>(context) {

                        @Override
                        public void onResponse(String response) {
                            Log.i("getUserID", response.toString());
                            AppConfig.setUserID(response);
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
    public void setCustom(Context context,String userID) {

        if (ifInternetOpen(context)) {

            StringRequest simpleRequest = new StringRequest(Request.Method.GET, host + "/FriendlyGreenHouseBackEnd/automatic/setWisdom?userID=" + userID+"&custom=1",
                    new ResponseAndContext<String>(context) {
                        @Override
                        public void onResponse(String response) {
                            Log.i("setCustom", response.toString());
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
    public void setAuto(Context context,String userID) {

        if (ifInternetOpen(context)) {

            StringRequest simpleRequest = new StringRequest(Request.Method.GET, host + "/FriendlyGreenHouseBackEnd/automatic/setWisdom?userID=" + userID+"&custom=0",
                    new ResponseAndContext<String>(context) {
                        @Override
                        public void onResponse(String response) {
                            Log.i("setAuto", response.toString());
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
    public void getMyFlowerSpecies(Context context,String userID) {

        if (ifInternetOpen(context)) {

            StringRequest simpleRequest = new StringRequest(Request.Method.GET, host + "/FriendlyGreenHouseBackEnd/speciesSet/getSpecies?userID=" + userID,
                    new ResponseAndContext<String>(context) {

                        @Override
                        public void onResponse(String response) {
                            Log.i("getChangeFlowerOrder", response.toString());
                            AppConfig.setSpecies(response);
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
    public void getMainStatus(Context context,String userID) {

        if (ifInternetOpen(context)) {

            JsonObjectRequest simpleRequest = new JsonObjectRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/stateServlet?userID="+userID,
                    new ResponseAndContext<JSONObject>(context) {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("getMainStatus", response.toString());
                            try {
                                AppConfig.setAirHum((String) response.get("airhum"));
                                AppConfig.setSetting((String) response.get("nowstate"));
                                AppConfig.setSoilHum((String) response.get("soilhum"));
                                AppConfig.setTemp((String) response.get("tem"));
                            } catch (JSONException e) {
                                Log.i("getMainStatus", e.getMessage());
                            }
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
    public void getDictionaryFlower(Context context) {

        if (ifInternetOpen(context)) {

            JsonArrayRequest simpleRequest = new JsonArrayRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/allEncyclopedia",
                    new ResponseAndContext<JSONArray>(context) {

                        @Override
                        public void onResponse(JSONArray response) {
                            Log.i("getDictionaryFlower", response.toString());
                            AppConfig.setFlowerdictionary(response);
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
    public void getphoto(Context context,String userID) {

        if (ifInternetOpen(context)) {

            ImageRequest imageRequest = new ImageRequest(

                    "http://140.121.197.132:8080/FriendlyGreenHouseBackEnd/getPhotoServlet?userID="+userID,

                    new Response.Listener<Bitmap>() {

                        @Override

                        public void onResponse(Bitmap response) {

                            Log.i("getphoto","Download Successful");
                            AppConfig.setPhoto(response);

                        }

                    }, 160, 150, Bitmap.Config.RGB_565, new Response.ErrorListener() {

                @Override

                public void onErrorResponse(VolleyError error) {

                 Log.i("getphoto","Download Error:"+error.getMessage());
                }

            });
            requeseQueue.add(imageRequest);
        }else{
            Resources res = context.getResources();
            AppConfig.setPhoto(BitmapFactory.decodeResource(res, R.drawable.camera));
        }
    }
    public void getImmediate(Context context,String userID) {

        if (ifInternetOpen(context)) {

            JsonObjectRequest simpleRequest = new JsonObjectRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/immediate/getImmediate?userID="+userID,
                    new ResponseAndContext<JSONObject>(context) {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("getImmediate", response.toString());
                            try {
                                AppConfig.setI_fan((String) response.get("fan"));
                                AppConfig.setI_photo((String) response.get("photo"));
                                AppConfig.setI_water((String) response.get("water"));
                            } catch (JSONException e) {
                                Log.i("getImmediate", e.getMessage());
                            }
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

    public void getFlowerStatus(Context context,String SpeciesID,String userID){


        if (ifInternetOpen(context)) {

            JsonObjectRequest simpleRequest = new JsonObjectRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/speciesSet/setSpecies?SpeciesID="+SpeciesID+"&userID="+userID,
                    new ResponseAndContext<JSONObject>(context) {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("sendChangeFlowerOrder", response.toString());
                            try {
                                if(((String)response.get("status")).equals("OK")){
                                    Log.i("sendChangeFlowerOrder", "Change Successful");
                                    Toast.makeText(context,"成功更改花種狀態",Toast.LENGTH_LONG);
                                }
                            } catch (JSONException e) {
                                Log.i("sendChangeFlowerOrder", e.getMessage());
                            }
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
    public void getControl(Context context,String userID){


        if (ifInternetOpen(context)) {

            JsonObjectRequest simpleRequest = new JsonObjectRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/automatic/getAutomaticState?userID="+userID,
                    new ResponseAndContext<JSONObject>(context) {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("getControl", response.toString());
                            AppConfig.setControl(response);

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
    public void getFlowerSpecies(Context context){

        if (ifInternetOpen(context)) {

            JsonArrayRequest simpleRequest = new JsonArrayRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/allEncyclopedia",
                    new ResponseAndContext<JSONArray>(context) {

                        @Override
                        public void onResponse(JSONArray response) {
                            Log.i("getFlowerSpecies", response.toString());
                            HashMap<String,String> responses=new HashMap<>();
                            for(int i = 0;i < response.length(); i++)
                            {
                                try {
                                    JSONObject data = response.getJSONObject(i);
                                    responses.put(data.getString("flower_name"),data.getString("flowerID"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            Log.i("getFlowerSpecies", "HashMap size: "+responses.size());
                            AppConfig.setFlowSpecies(responses);
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
    public void setCustomDetailSetting(Context context,String userID,String lowTem,String highTem,String lowHum,String highHum){


        if (ifInternetOpen(context)) {

            JsonObjectRequest simpleRequest = new JsonObjectRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/automatic/setCustom?userID="+userID+"&lowTem="+lowTem+"&highTem="+highTem+"&lowHum="+lowHum+"&highHum="+highHum,
                    new ResponseAndContext<JSONObject>(context) {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("sendChangeFlowerOrder", response.toString());
                            try {
                                if(((String)response.get("status")).equals("OK")){
                                    Log.i("setCustomSetting", "Change Successful");
                                    Toast.makeText(context,"成功設定客製化狀態",Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                Log.i("sendChangeFlowerOrder", e.getMessage());
                            }
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
    public void setImmediateSetting(Context context,String userID,String TAG){

        if (ifInternetOpen(context)) {

            JsonObjectRequest simpleRequest = new JsonObjectRequest(Request.Method.GET, host+"/FriendlyGreenHouseBackEnd/immediate/setImmediate?userID="+userID+"&immParameter=10&TAG="+TAG,
                    new ResponseAndContext<JSONObject>(context) {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("setImmediateSetting", response.toString());
                            try {
                                if(((String)response.get("status")).equals("OK")){
                                    Log.i("setImmediateSetting", "Change Successful");
                                    Toast.makeText(context,"成功設定即時狀態",Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                Log.i("sendChangeFlowerOrder", e.getMessage());
                            }
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
