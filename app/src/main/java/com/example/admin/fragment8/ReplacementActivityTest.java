package com.example.admin.fragment8;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ReplacementActivityTest extends AppCompatActivity {
    int count = 2;
    ArrayList<Replacement> replacements = new ArrayList<>();
    ActionBar actionBar;
    ProgressBar progressBar;
    TextView textView;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        progressBar = findViewById(R.id.pb_loading_indicator);
        textView = (TextView) findViewById(R.id.text_not_net);
        if(isOnline()){
            new PrivatteAsynTaskReplacement().execute();
        }else {
            textView.setText("Немає інтернет підключення");
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Заміни");
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Integer> days = new ArrayList<>();
        int count;
        private String[]tabs;
        public SectionsPagerAdapter(FragmentManager fm, int count){
            super(fm);
            this.count = count;
            findName();
            tabs = new String[days.size()];
            for (int i =0; i<days.size();i++){
                tabs[i]= String.valueOf(days.get(i));
            }
        }
        @Override
        public Fragment getItem(int position){
            return PageFragment3.newInstance2(position, replacements,days.get(position));
        }
        @Override
        public int getCount(){
            return count;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position){
            String s = tabs[position];
            String ret = "";
            for(int i=0; i<replacements.size();i++){
                if(s.equals(replacements.get(i).day)){
                    String m = replacements.get(i).month;
                    int im = Integer.parseInt(replacements.get(i).month);
                    if(im<10){
                        m = "0"+replacements.get(i).month;
                    }
                    ret = replacements.get(i).day+" "+m;
                }
            }
            return ret;
        }
        private void findName(){
            if(replacements.size()>0){
                for (int i =0; i<replacements.size();i++){
                    days.add(Integer.parseInt(replacements.get(i).day));
                }
            }
            for(int i=0; i<days.size(); i++) {
                for (int j=0; j<days.size(); j++) {
                    if(i==j){

                    }else {
                        if (days.get(i) == days.get(j)) {
                            days.remove(j);
                            if(j>0) {
                                j--;
                            }
                        }
                    }
                }
            }
            sotrToDay();
            sotrToMonth();
        }
        private void sotrToDay (){
            boolean fl = false;
            for(int i=0; i<days.size()-1; i++) {
                if(days.get(i)>days.get(i+1)){
                    int temp = days.get(i);
                    days.set(i,days.get(i+1));
                    days.set(i+1,temp);
                    fl = true;
                }
            }
            if(fl){
                sotrToDay();
            }
        }
        private void sotrToMonth (){
            boolean fl = false;
            for(int i=0; i<days.size()-1; i++) {
                if(getMonthToDay(i)>(getMonthToDay(i+1))){
                    int temp = days.get(i);
                    days.set(i,days.get(i+1));
                    days.set(i+1,temp);
                    fl = true;
                }
            }
            if(fl){
                sotrToMonth();
            }
        }
        private int getMonthToDay(int x){
            int d = days.get(x);
            int m =0;
            for(int j=0;j<replacements.size();j++){
                if(d==Integer.parseInt(replacements.get(j).day)){
                    m = Integer.parseInt(replacements.get(j).month);
                    return m;
                }
            }
            return 0;
        }
    }
    class PrivatteAsynTaskReplacement extends AsyncTask<String,Void,String> {
        String answerHTTP;
        String s;
        int j;
        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpsURLConnection urlConnection = null;
            String server = "https://damp-sierra-10878.herokuapp.com/JsonReplacement";
            try {
                url = new URL(server);
                urlConnection = (HttpsURLConnection) url.openConnection();
                int responseCode = urlConnection.getResponseCode();
                if(responseCode == HttpsURLConnection.HTTP_OK){
                    answerHTTP = convertInputStreamToString(urlConnection.getInputStream());
                    try {
                        JSONObject jsonResponse = new JSONObject(answerHTTP);
                        JSONArray jsonArray = jsonResponse.getJSONArray("response");
                        s = jsonResponse.toString();
                        j = jsonArray.length();
                        for (int i = 0; i < j; i++) {
                            JSONObject one_replacement = jsonArray.getJSONObject(i);
                            String group = one_replacement.getString("group");
                            String year = one_replacement.getString("year");
                            String month =one_replacement.getString("month");
                            String day = one_replacement.getString("day");
                            String number =one_replacement.getString("number");
                            String old_name = one_replacement.getString("old_name");
                            String old_teacher1 = one_replacement.getString("old_teacher1");
                            String old_teacher2 = one_replacement.getString("old_teacher2");
                            String old_study = one_replacement.getString("old_study");
                            String new_name = one_replacement.getString("new_name");
                            String new_teacher1 = one_replacement.getString("new_teacher1");
                            String new_teacher2 = one_replacement.getString("new_teacher2");
                            String new_study = one_replacement.getString("new_study");
                            replacements.add(new Replacement(group,year,month,day,number,
                                    old_name,old_teacher1,old_teacher2,old_study,
                                    new_name,new_teacher1,new_teacher2,new_study));
                        }
                    }catch (JSONException e){
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
            count = findAnyDay(replacements);
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),count);
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            TabLayout tabLayout = findViewById(R.id.tabs);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            tabLayout.setupWithViewPager(mViewPager);
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
        public int findAnyDay(ArrayList<Replacement> replacements){
            ArrayList<Integer> days = new ArrayList<>();
            if(replacements.size()>0){
                for (int i =0; i<replacements.size();i++){
                   days.add(Integer.parseInt(replacements.get(i).day));
                }
            }
            for(int i=0; i<days.size(); i++) {
                System.out.println(days.get(i));
                for (int j=0; j<days.size(); j++) {
                    if(i==j){
                    }else {
                        if (days.get(i) == days.get(j)) {

                            days.remove(j);
                            j--;
                        }
                    }
                }
            }
            return days.size();
        }
    }
}
