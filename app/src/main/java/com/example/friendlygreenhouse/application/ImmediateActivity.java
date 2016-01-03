package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.friendlygreenhouse.application.api.AppConfig;
import com.example.friendlygreenhouse.application.api.CallAPIhelper;

/**
 * Created by ser on 2015/12/31.
 */
public class ImmediateActivity extends Activity {
    private ImageButton fan_on;
    private ImageButton fan_off;
    private ImageButton water;
    private ImageButton camera;
    CallAPIhelper APIhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediate);

        APIhelper=new CallAPIhelper();

        fan_on =(ImageButton) findViewById(R.id.fan_on);
        fan_off =(ImageButton) findViewById(R.id.fan_off);
        water =(ImageButton) findViewById(R.id.water);
        camera =(ImageButton) findViewById(R.id.camera);

        fan_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                APIhelper.setImmediateSetting(getBaseContext(), AppConfig.getUserID(),"0");
                Toast.makeText(getBaseContext(),"成功開啟風扇",Toast.LENGTH_LONG).show();
            }
        });
        fan_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                APIhelper.setImmediateSetting(getBaseContext(), AppConfig.getUserID(),"1");
                Toast.makeText(getBaseContext(), "成功關閉風扇", Toast.LENGTH_LONG).show();
            }
        });
        water.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                APIhelper.setImmediateSetting(getBaseContext(), AppConfig.getUserID(),"2");
                Toast.makeText(getBaseContext(), "成功澆水", Toast.LENGTH_LONG).show();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                APIhelper.setImmediateSetting(getBaseContext(), AppConfig.getUserID(),"3");
                Toast.makeText(getBaseContext(), "成功照相", Toast.LENGTH_LONG).show();
            }
        });
    }

}
