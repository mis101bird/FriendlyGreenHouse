package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends Activity {

    ImageView photo;

    TextView status_temp;
    TextView status_soilwater;
    TextView status_airwater;

    TextView setting_temp;
    TextView setting_type;
    TextView setting_water;

    HashMap<String,String> flowerData=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo = (ImageView)findViewById(R.id.photo);

        status_temp = (TextView)findViewById(R.id.evn_temperature_value);
        status_soilwater = (TextView)findViewById(R.id.soil_water_value);
        status_airwater = (TextView)findViewById(R.id.air_water_value);

        setting_temp = (TextView)findViewById(R.id.temperature_status);
        setting_water = (TextView)findViewById(R.id.water_status_value);
        setting_type = (TextView)findViewById(R.id.setting_status_value);

        Button change_flower = (Button) findViewById(R.id.change_flower);
        change_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getParent(), ChangeFlowerActivity.class);
                startActivity(info);
                MainActivity.this.finish();

            }
        });
        Button immediately = (Button) findViewById(R.id.immediately);
        immediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getParent(),ImmediateActivity.class);
                startActivity(info);
                MainActivity.this.finish();

            }
        });
        Button dictionary = (Button) findViewById(R.id.dictionary);
        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getParent(),DictionaryActivity.class);
                startActivity(info);
                MainActivity.this.finish();

            }
        });
        Button diary = (Button) findViewById(R.id.diary);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getParent(),DiaryActivity.class);
                startActivity(info);
                MainActivity.this.finish();

            }
        });
        Button setting = (Button) findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getParent(),SettingActivity.class);
                startActivity(info);
                MainActivity.this.finish();

            }
        });
    }
    public void changeStatusBoard(){
       new StatusLoading().execute();
    }
    private class StatusLoading extends AsyncTask<Void, Void, Void> { //Add the first 10 items

        @Override
        protected Void doInBackground(Void... params) {
            while(true){
                flowerData.clear();
                //解析花狀態的JSON的內容
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Log.i("reset status Error",e.getMessage());
                }
            }

        }

        @Override
        protected void onPostExecute(Void a) {


        }
    }
}
