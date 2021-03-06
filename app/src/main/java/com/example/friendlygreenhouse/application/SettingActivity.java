package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.friendlygreenhouse.application.api.AppConfig;
import com.example.friendlygreenhouse.application.api.CallAPIhelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ser on 2015/12/31.
 */
public class SettingActivity extends Activity {
    Switch pot;
    Switch fertilize;
    Switch bad_environment;

    Switch custom;
    Switch knowledge;

    Button detail;

    boolean initFlag=true;
    CallAPIhelper helper=new CallAPIhelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        bad_environment=(Switch)findViewById(R.id.bad_environment);
        detail=(Button)findViewById(R.id.detail);
        bad_environment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }

            }
        });

        pot=(Switch)findViewById(R.id.change_pot);
        pot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                } else {

                }

            }
        });

        fertilize=(Switch)findViewById(R.id.fertilize);
        fertilize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                } else {

                }

            }
        });

        custom=(Switch)findViewById(R.id.custom);
        try {
            if(AppConfig.getControl().get("custom").equals("true")){
                custom.setChecked(true);
                detail.setVisibility(View.VISIBLE);
            }else{
                custom.setChecked(false);
                detail.setVisibility(View.INVISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        custom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked &&initFlag==false) {

                    helper.setCustom(getBaseContext(), AppConfig.getUserID());
                    knowledge.setChecked(false);
                    detail.setVisibility(View.VISIBLE);

                }else{
                    knowledge.setChecked(true);
                    detail.setVisibility(View.INVISIBLE);
                }
            }
        });


        knowledge=(Switch)findViewById(R.id.knowledge);
        try {
            if(AppConfig.getControl().get("automatic").equals("true")){
                knowledge.setChecked(true);
            }else{
                knowledge.setChecked(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        knowledge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    helper.setAuto(getBaseContext(), AppConfig.getUserID());
                    custom.setChecked(false);
                } else {

                }
            }
        });
        detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent info = new Intent();
                info.setClass(getBaseContext(), CustomerActivity.class);
                startActivity(info);
            }
        });

    }



    @Override
    protected void onResume() {

        super.onResume();
        initFlag=false;
    }
}
