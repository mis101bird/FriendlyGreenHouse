package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.friendlygreenhouse.application.api.AppConfig;
import com.example.friendlygreenhouse.application.api.CallAPIhelper;

import org.json.JSONArray;

/**
 * Created by ser on 2015/12/31.
 */
public class DictionaryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        //呼叫所有養花大百科的資料
        JSONArray dic=AppConfig.getFlowerdictionary();
        Log.i("DictionaryActivity",dic.toString());
        
    }
}
