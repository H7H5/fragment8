package com.example.admin.fragment8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    SQLiteDatabase database;
    String str = "";
    final String LOG_TAG = "myLogs";
    ListView lvMain;
    String[] te;
    ArrayList<String> teacher = new ArrayList<>();
    ArrayList<String> teacher3 = new ArrayList<>();
    ArrayList<String> teacher1 = new ArrayList<>();
    ActionBar actionBar;
    ProgressBar progressBar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        progressBar = findViewById(R.id.pb_loading_indicator3);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        read();
        Collections.sort(teacher);
        Collections.sort(teacher3);
        for (int i = 0; i <teacher3.size();i++){
            teacher.add(teacher3.get(i));
        }
        Collections.sort(teacher);
        render();
    }
    public void read(){
        Cursor cursor = database.query(DBHelper.TABLE_SAVE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int grouplIndex = cursor.getColumnIndex(DBHelper.KEY_GROUP);
            int dayIndex = cursor.getColumnIndex(DBHelper.KEY_DAY);
            int numberIndex = cursor.getColumnIndex(DBHelper.KEY_NUMBER);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int teacherIndex = cursor.getColumnIndex(DBHelper.KEY_TEACHER);
            int teacher2Index = cursor.getColumnIndex(DBHelper.KEY_TEACHER2);
            int studyIndex = cursor.getColumnIndex(DBHelper.KEY_STUDY);
            int numberatorIndex = cursor.getColumnIndex(DBHelper.KEY_NUMERATOR);
            do {
                teacher.add(cursor.getString(teacherIndex));
                if(!cursor.getString(teacher2Index).equals("")) {
                    if(!cursor.getString(teacher2Index).equals("null")) {
                        teacher3.add(cursor.getString(teacher2Index));
                    }
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onClick(View arg0) {
        if(lvMain.getCheckedItemCount()>0) {
            Log.d(LOG_TAG, "checked: " + te[lvMain.getCheckedItemPosition()]);
            Intent intent = new Intent(this, MainActivity5.class);
            String f = ""+lvMain.getCheckedItemPosition();
            intent.putExtra("id", f);
            intent.putExtra("teacher", te[lvMain.getCheckedItemPosition()]);
            startActivity(intent);
        }
    }
    public void render(){
        Iterator iterator = teacher.iterator();
        while (iterator.hasNext()) {
            String o = (String) iterator.next();
            if(!teacher1.contains(o)) teacher1.add(o);
        }
        te = new String[teacher1.size()];
        for(int i=0; i <teacher1.size();i++){
            te[i] = teacher1.get(i);
        }
        lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                R.layout.list_item, te);
        lvMain.setAdapter(adapter);
        Button btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);
    }
}


