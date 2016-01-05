package com.example.friendlygreenhouse.application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.friendlygreenhouse.application.LocalDatabase.SQLiteHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ser on 2015/12/31.
 */
public class DiaryActivity extends Activity{
    private static final String DB_FILE = "friends.db",
            DB_TABLE = "friends";
    private SQLiteDatabase mFriendDb;

    private EditText mEdtName,
            mEdtSex,
            mEdtAddr,
            mEdtList;

    private Button mBtnAdd,
            mBtnQuery,
            mBtnList;
    private  final List<String> dd=new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary2);

        SQLiteHandler friendDbOpenHelper =
                new SQLiteHandler(getApplicationContext(), DB_FILE, null, 1);
        mFriendDb = friendDbOpenHelper.getWritableDatabase();

        // 檢查資料表是否已經存在，如果不存在，就建立一個。
        Cursor cursor = mFriendDb.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" +
                        DB_TABLE + "'", null);

        if(cursor != null) {
            if(cursor.getCount() == 0)	// 沒有資料表，要建立一個資料表。
                mFriendDb.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                        "_id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "sex TEXT," +
                        "address TEXT);");

            cursor.close();
        }

        mEdtName = (EditText)findViewById(R.id.edtName);
        mEdtSex = (EditText)findViewById(R.id.edtSex);
        mEdtAddr = (EditText)findViewById(R.id.edtAddr);
        mEdtList = (EditText)findViewById(R.id.edtList);

        mBtnAdd = (Button)findViewById(R.id.btnAdd);
        mBtnQuery = (Button)findViewById(R.id.btnQuery);
        mBtnList = (Button)findViewById(R.id.btnList);

        mBtnAdd.setOnClickListener(btnAddOnClick);
        mBtnQuery.setOnClickListener(btnQueryOnClick);
        mBtnList.setOnClickListener(btnListOnClick);

/*
        Cursor c = mFriendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                "address"}, 	null, null, null, null, null, null);

        if (c == null)
            return;

        if (c.getCount() == 0) {
            mEdtList.setText("");
            Toast.makeText(DiaryActivity.this, "沒有資料", Toast.LENGTH_LONG)
                    .show();
        }
        else {
            c.moveToFirst();
            dd.add(c.getString(0) + c.getString(1)  + c.getString(2));

            while (c.moveToNext())
                dd.add(c.getString(0) + c.getString(1)  +
                        c.getString(2));
        }

        ListView list=(ListView) findViewById(R.id.listView2);

        ListAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,dd);

        list.setAdapter(ad);
*/
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mFriendDb.close();
    }

    private View.OnClickListener btnAddOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            ContentValues newRow = new ContentValues();
            newRow.put("name", mEdtName.getText().toString());
            newRow.put("sex", mEdtSex.getText().toString());
            newRow.put("address", mEdtAddr.getText().toString());
            mFriendDb.insert(DB_TABLE, null, newRow);
        }
    };

    private View.OnClickListener btnQueryOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            Cursor c = null;

            if (!mEdtName.getText().toString().equals("")) {
                c = mFriendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, "name=" + "\"" + mEdtName.getText().toString()
                        + "\"", null, null, null, null, null);
            } else if (!mEdtSex.getText().toString().equals("")) {
                c = mFriendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, "sex=" + "\"" + mEdtSex.getText().toString()
                        + "\"", null, null, null, null, null);
            } else if (!mEdtAddr.getText().toString().equals("")) {
                c = mFriendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, "address=" + "\"" + mEdtAddr.getText().toString()
                        + "\"", null, null, null, null, null);
            }

            if (c == null)
                return;

            if (c.getCount() == 0) {
                mEdtList.setText("");
                Toast.makeText(DiaryActivity.this, "沒有這筆資料", Toast.LENGTH_LONG)
                        .show();
            } else {
                c.moveToFirst();
                mEdtList.setText(c.getString(0) + c.getString(1)  + c.getString(2));

                while (c.moveToNext())
                    mEdtList.append("\n" + c.getString(0) + c.getString(1) +
                            c.getString(2));
            }
        }
    };

    private View.OnClickListener btnListOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Cursor c = mFriendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                    "address"}, 	null, null, null, null, null, null);

            if (c == null)
                return;

            if (c.getCount() == 0) {
                mEdtList.setText("");
                Toast.makeText(DiaryActivity.this, "沒有資料", Toast.LENGTH_LONG)
                        .show();
            }
            else {
                c.moveToFirst();
                mEdtList.setText(c.getString(0) + c.getString(1)  + c.getString(2));

                while (c.moveToNext())
                    mEdtList.append("\n" + c.getString(0) + c.getString(1)  +
                            c.getString(2));
            }
        }
    };

}
