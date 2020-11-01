package com.example.admin.fragment8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity4 extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase database;
    ArrayList<Lesson> schedulesGroup = new ArrayList<>();
    String selectGroup = "2-222";
    ActionBar actionBar;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent = getIntent();
        selectGroup = intent.getStringExtra("group");
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        read();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Розклад для "+selectGroup+" групи");
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
                if(cursor.getString(grouplIndex).equals(selectGroup)){
                    schedulesGroup.add(new Lesson(
                            cursor.getString(nameIndex),
                            cursor.getString(grouplIndex),
                            cursor.getString(teacherIndex),
                            cursor.getString(teacher2Index),
                            cursor.getString(dayIndex),
                            cursor.getString(studyIndex),
                            cursor.getString(numberIndex),
                            cursor.getString(numberatorIndex)));
                }
            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "000000000000000000000000000 rows");
        }
        cursor.close();
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
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return PageFragment2.newInstance2(position, 10,1,schedulesGroup);
                case 1:
                    return PageFragment2.newInstance2(position, 11,1,schedulesGroup);
                case 2:
                    return PageFragment2.newInstance2(position, 12,1,schedulesGroup);
                case 3:
                    return PageFragment2.newInstance2(position, 13,1,schedulesGroup);
                case 4:
                    return PageFragment2.newInstance2(position, 14,1,schedulesGroup);
                default:
                    return PageFragment2.newInstance2(position, 10,1,schedulesGroup);
            }
        }
        @Override
        public int getCount() {
            return 5;
        }
    }
}