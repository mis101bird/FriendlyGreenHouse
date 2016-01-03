package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.friendlygreenhouse.application.api.AppConfig;
import com.example.friendlygreenhouse.application.api.CallAPIhelper;

/**
 * Created by ser on 2015/12/31.
 */
public class CustomerActivity extends Activity {
    EditText lowHum ;
    EditText lowTem ;
    EditText highHum ;
    EditText highTem ;
    Button submit;
    CallAPIhelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        helper=new CallAPIhelper();

        lowHum = (EditText)findViewById(R.id.lowHum);
        lowTem = (EditText)findViewById(R.id.lowTem);
        highHum = (EditText)findViewById(R.id.highHum);
        highTem = (EditText)findViewById(R.id.highTem);
        submit=(Button)findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lowHum_v="80";
                String highHum_v="85";
                String lowTem_v="15";
                String highTem_v="20";
                if(lowHum.getText()!=null){
                    lowHum_v=lowHum.getText().toString();
                }
                if(highHum.getText()!=null){
                    highHum_v=highHum.getText().toString();
                }
                if(lowTem.getText()!=null){
                    lowTem_v=lowTem.getText().toString();
                }
                if(highTem.getText()!=null){
                    highTem_v=highTem.getText().toString();
                }
                helper.setCustomDetailSetting(getBaseContext(), AppConfig.getUserID(),lowTem_v,highTem_v,lowHum_v,highHum_v);
                helper.setCustom(getBaseContext(), AppConfig.getUserID());
                helper.getControl(getBaseContext(), AppConfig.getUserID());
                CustomerActivity.this.finish();
            }
        });
    }
}
