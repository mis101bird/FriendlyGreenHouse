package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.friendlygreenhouse.application.api.AppConfig;
import com.example.friendlygreenhouse.application.api.CallAPIhelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ser on 2015/12/31.
 */
public class ChangeFlowerActivity extends Activity {

    HashMap<String,String> species;
    CallAPIhelper helper=new CallAPIhelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeflower);

        species= AppConfig.getFlowSpecies();

        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        if(species!=null){
            int i=0;
            for(Map.Entry<String, String> entry : species.entrySet()) {
                String flowerName = entry.getKey();
                RadioButton tv=new RadioButton(this);
                tv.setId(i);
                tv.setText(flowerName);
                if(flowerName.equals(AppConfig.getSpecies())){
                    tv.setChecked(true);
                }
                tv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {

                        //handle the boolean flag here.
                        if (arg1 == true) {
                            String flowerID=species.get(arg0.getText());
                            helper.sendChangeFlowerOrder(getBaseContext(),flowerID,AppConfig.getUserID());
                        }

                        else {

                        }


                    }
                });
                i++;
                radioGroup.addView(tv);

            }

        }

    }

}
