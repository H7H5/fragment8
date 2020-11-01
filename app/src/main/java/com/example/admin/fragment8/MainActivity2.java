package com.example.admin.fragment8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button btnChecked;
    DBHelper dbHelper;
    SQLiteDatabase database;
    String str = "";
    final String LOG_TAG = "mLog";
    ListView lvMain;
    String[] gr;
    ArrayList<String> group = new ArrayList<>();
    ArrayList<String> group1 = new ArrayList<>();
    androidx.appcompat.app.ActionBar actionBar;
    ProgressBar progressBar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);
        progressBar = findViewById(R.id.pb_loading_indicator);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        group.add(str);
        read();
        group.remove(0);
        Collections.sort(group);
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
                group.add(cursor.getString(grouplIndex));
            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "000000000000000000000000000 rows");
        }
        cursor.close();
    }
    public void render(){
        Iterator iterator = group.iterator();
        while (iterator.hasNext()) {
            String o = (String) iterator.next();
            if(!group1.contains(o)) group1.add(o);
        }
        gr = new String[group1.size()];
        for(int i=0; i <group1.size();i++){
            gr[i] = group1.get(i);
        }
        lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                R.layout.list_item, gr);
        lvMain.setAdapter(adapter);

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
            Log.d(LOG_TAG, "checked: " + gr[lvMain.getCheckedItemPosition()]);
            Intent intent = new Intent(this, MainActivity4.class);
            intent.putExtra("group", gr[lvMain.getCheckedItemPosition()]);
            startActivity(intent);
        }
    }
}
