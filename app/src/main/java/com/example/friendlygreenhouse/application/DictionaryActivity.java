package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.friendlygreenhouse.application.api.AppConfig;
import com.example.friendlygreenhouse.application.api.CallAPIhelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ser on 2015/12/31.
 */
public class DictionaryActivity extends Activity {

    ArrayList<HashMap<String, String>> DicItems = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;
    protected ListView itcItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        //呼叫所有養花大百科的資料
        JSONArray dic=AppConfig.getFlowerdictionary();
        Log.i("DictionaryActivity", dic.toString());

        final List<String> dictionaryData=new ArrayList<String>();
        final List<String> dictionaryDataURL=new ArrayList<String>();

        for(int i=0;i<dic.length();i++)
        {

            try {

                HashMap<String, String>  dictionaryItem = new HashMap<String, String>();
                dictionaryDataURL.add(dic.getJSONObject(i).get("infoURL").toString());
                dictionaryData.add(dic.getJSONObject(i).get("flower_name").toString());
                dictionaryItem.put(dic.getJSONObject(i).get("flower_name").toString(), dic.getJSONObject(i).get("infoURL").toString());
                DicItems.add(dictionaryItem);



            }catch(JSONException e)
            {

            }

        }

        ListView list=(ListView) findViewById(R.id.listView);

        ListAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,dictionaryData);

        list.setAdapter(ad);







        class ListListener implements AdapterView.OnItemClickListener{


            List<String> data;
            Activity activity;


            public ListListener(List<String> data,Activity activity)
            {
                this.data = data;
                this.activity = activity;

            }


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println(dictionaryDataURL.get(i).toString());

                if(dictionaryDataURL.get(i).toString().equals("")!=true) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);

                    intent.setData(Uri.parse(dictionaryDataURL.get(i).toString()));

                    activity.startActivity(intent);
                }
                else
                {
                    Context context2 = getApplication();

                    CharSequence text2 = "現在暫無此花種的資料";

                    int duration2 = Toast.LENGTH_SHORT;

                    Toast.makeText(context2, text2, duration2).show();
                }


            }
        }


        list.setOnItemClickListener(new ListListener(dictionaryData, this));

    }
}