package com.example.admin.fragment8;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity implements View.OnClickListener  {
    Button butn1;
    final String LOG_TAG = "mLog";
    ArrayList<Lesson> schedulesGroup = new ArrayList<>();
    String str = "";
    ListView lvMain;
    String[] gr;
    ArrayList<String> group = new ArrayList<>();
    androidx.appcompat.app.ActionBar actionBar;
    ProgressBar progressBar;
    TextView textView;
    DBHelper dbHelper;
    SQLiteDatabase database;
    ContentValues contentValues = new ContentValues();
    int e =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        butn1 = (Button)findViewById(R.id.button6);
        butn1.setOnClickListener(this);
        butn1.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.pb_loading_indicator);
        textView = (TextView) findViewById(R.id.download);
        if(isOnline()){
            new PrivatteAsynTask6().execute();
        }else {
            textView.setText("Немає інтернет підключення");
            butn1.setVisibility(View.VISIBLE);
        }
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button6:
                Intent intent = new Intent(this,MainActivity1.class);
                startActivity(intent);
                break;
        }
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
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
                e++;
                Log.d(LOG_TAG, "mLog"+ cursor.getInt(idIndex)+
                        ", group = " + cursor.getString(grouplIndex) +
                        ", day = " + cursor.getString(dayIndex) +
                        ", number = " + cursor.getString(numberIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", teacher = " + cursor.getString(teacherIndex) +
                        ", teacher2 = " + cursor.getString(teacher2Index) +
                        ", study = " + cursor.getString(studyIndex) +
                        ", numberator = " + cursor.getString(numberatorIndex));
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
    class PrivatteAsynTask6 extends AsyncTask<String,Void,String> {
        String answerHTTP;
        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpsURLConnection urlConnection = null;
            String server = "https://damp-sierra-10878.herokuapp.com/select?groupp=groups_json";
            try {
                url = new URL(server);
                urlConnection = (HttpsURLConnection) url.openConnection();
                int responseCode = urlConnection.getResponseCode();
                if(responseCode == HttpsURLConnection.HTTP_OK){
                    answerHTTP = convertInputStreamToString(urlConnection.getInputStream());
                    try {
                        JSONObject jsonResponse = new JSONObject(answerHTTP);
                        JSONArray jsonArray = jsonResponse.getJSONArray("response");
                        JSONObject userInfo = jsonArray.getJSONObject(0);
                        int j = userInfo.length();
                        for (int i = 0; i<j;i++) {
                            String v = ""+i;
                            str = userInfo.getString(v);
                            group.add(str);
                        }
                        group.add(group.get(0));
                        group.remove(0);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    gr = new String[group.size()];
                    for (int i=0;i<group.size();i++){
                        gr[i]=group.get(i);
                    }
                }
                for (int r =0; r<group.size();r++) {
                    server = "https://damp-sierra-10878.herokuapp.com/select/" + group.get(r);
                    try {
                        url = new URL(server);
                        urlConnection = (HttpsURLConnection) url.openConnection();
                        int responseCode1 = urlConnection.getResponseCode();
                        if (responseCode1 == HttpsURLConnection.HTTP_OK) {
                            answerHTTP = convertInputStreamToString(urlConnection.getInputStream());
                            try {
                                JSONObject jsonResponse = new JSONObject(answerHTTP);
                                JSONArray jsonArray = jsonResponse.getJSONArray("response");
                                int j = jsonArray.length();
                                for (int i = 0; i < j; i++) {
                                    JSONObject userInfo = jsonArray.getJSONObject(i);
                                    String name = userInfo.getString("name");
                                    String group = userInfo.getString("group");
                                    String teacher = userInfo.getString("teacher");
                                    String teacher2 = userInfo.getString("teacher2");
                                    String day = userInfo.getString("day");
                                    String study = userInfo.getString("study");
                                    String number = userInfo.getString("number");
                                    String fraction = userInfo.getString("numerator");
                                    schedulesGroup.add(new Lesson(name, group, teacher, teacher2, day, study, number, fraction));
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String response){
            progressBar.setVisibility(View.INVISIBLE);
            if(schedulesGroup.size()>0){
                database.delete(DBHelper.TABLE_SAVE, null, null);
                for(int i = 0; i <schedulesGroup.size();i++) {
                    contentValues.put(DBHelper.KEY_GROUP, schedulesGroup.get(i).group);
                    contentValues.put(DBHelper.KEY_DAY, schedulesGroup.get(i).day);
                    contentValues.put(DBHelper.KEY_NUMBER, schedulesGroup.get(i).numbers);
                    contentValues.put(DBHelper.KEY_NAME, schedulesGroup.get(i).name);
                    contentValues.put(DBHelper.KEY_TEACHER, schedulesGroup.get(i).teacher);
                    contentValues.put(DBHelper.KEY_TEACHER2, schedulesGroup.get(i).teacher2);
                    contentValues.put(DBHelper.KEY_STUDY, schedulesGroup.get(i).study);
                    contentValues.put(DBHelper.KEY_NUMERATOR, schedulesGroup.get(i).fraction);
                    database.insert(DBHelper.TABLE_SAVE, null, contentValues);
                }
            }
            read();
            textView.setText("Розклад оновлено");
            butn1.setVisibility(View.VISIBLE);
            dbHelper.close();
        }
        private String convertInputStreamToString(InputStream in) {
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }
    }
}
