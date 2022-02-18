package com.example.admin.fragment8;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {
    Button butn1;
    Button butn2;
    Button butn3;
    Button butn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        butn1 = (Button)findViewById(R.id.button);
        butn2 = (Button)findViewById(R.id.button2);
        butn3 = (Button)findViewById(R.id.button3);
        butn4 = (Button)findViewById(R.id.button4);
        butn1.setOnClickListener(this);
        butn2.setOnClickListener(this);
        butn3.setOnClickListener(this);
        butn4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                Intent intent = new Intent(this,MainActivity2.class);
                startActivity(intent);
                break;
            case R.id.button:
                Intent intent2 = new Intent(this,MainActivity3.class);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(this,ReplacementActivityTest.class);
                startActivity(intent4);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(this,MainActivity6.class);
                startActivity(intent3);
                break;
        }
    }
}
