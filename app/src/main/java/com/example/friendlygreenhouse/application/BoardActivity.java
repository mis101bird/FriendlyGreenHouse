package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.friendlygreenhouse.application.api.AppConfig;
import com.example.friendlygreenhouse.application.api.CallAPIhelper;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class BoardActivity extends Activity {

    ImageView photo;

    TextView status_temp;
    TextView status_soilwater;
    TextView status_airwater;

    TextView setting_type;
    Timer timer=new Timer();


    CallAPIhelper helper = new CallAPIhelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化先下載資料
        helper.getFlowerSpecies(this);
        helper.getMyFlowerSpecies(this, AppConfig.getUserID());
        helper.getControl(getBaseContext(), AppConfig.getUserID());
        helper.getDictionaryFlower(getBaseContext());

        photo = (ImageView)findViewById(R.id.photo);

        status_temp = (TextView)findViewById(R.id.evn_temperature_value);
        status_soilwater = (TextView)findViewById(R.id.soil_water_value);
        status_airwater = (TextView)findViewById(R.id.air_water_value);

        setting_type = (TextView)findViewById(R.id.setting_status_value);

        Button change_flower = (Button) findViewById(R.id.change_flower);
        change_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getBaseContext(), ChangeFlowerActivity.class);
                startActivity(info);


            }
        });
        Button immediately = (Button) findViewById(R.id.immediately);
        immediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getBaseContext(), ImmediateActivity.class);
                startActivity(info);


            }
        });
        Button dictionary = (Button) findViewById(R.id.dictionary);
        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getBaseContext(), DictionaryActivity.class);
                startActivity(info);


            }
        });
        Button diary = (Button) findViewById(R.id.diary);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getBaseContext(), DiaryActivity.class);
                startActivity(info);


            }
        });
        Button setting = (Button) findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getBaseContext(), SettingActivity.class);
                startActivity(info);


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeStatusBoard();//刷新Board
    }

    public void changeStatusBoard(){

        timer.schedule(new TimerTask() {
            CallAPIhelper helper = new CallAPIhelper();

            @Override
            public void run() {
                //helper.getMainStatus(getBaseContext(),AppConfig.getUserID());
                Log.i("getMainStatus", "in Time on Setting: ");
                helper.getMainStatus(getBaseContext(), AppConfig.getUserID());
                helper.getControl(getBaseContext(), AppConfig.getUserID());
                new ChangeUIstatus().execute();

            }
        }, 500);
    }
    class ChangeUIstatus extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if(AppConfig.getSetting()!=null){
                if(AppConfig.getSetting().equals("automatic")){
                    setting_type.setText("智慧化");
                }else{
                    setting_type.setText("客製化");
                }
                Log.i("getMainStatus", "Setting: "+AppConfig.getSetting());
            }
            if(AppConfig.getSoilHum()!=null){
                status_soilwater.setText(AppConfig.getSoilHum()+"%");
                Log.i("getMainStatus", "Setting: " + AppConfig.getSoilHum());
            }
            if(AppConfig.getAirHum()!=null){
                status_airwater.setText(AppConfig.getAirHum()+"%");
                Log.i("getMainStatus", "Setting: " + AppConfig.getAirHum());
            }
            if(AppConfig.getTemp()!=null){
                status_temp.setText(AppConfig.getTemp()+"度");
                Log.i("getMainStatus", "Setting: " +AppConfig.getTemp());
            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
