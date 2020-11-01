package com.example.admin.fragment8;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.ArrayList;

public class TestTask {
    androidx.cardview.widget.CardView cardView;
    androidx.cardview.widget.CardView cardView2;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    Context context;
    String days;
    int number;
    String names;
    String group;
    String teacher;
    String teacher2;
    String study;
    int width = 1;
    ArrayList<Lesson> schedulesGroup;
    public TestTask(ArrayList<Lesson> schedulesGroup, androidx.cardview.widget.CardView cardView2,
                    androidx.cardview.widget.CardView cardView, TextView textView1, TextView textView2,
                    TextView textView3, TextView textView4,
                    TextView textView5, TextView textView6, TextView textView7,
                    TextView textView8, TextView textView9,
                    TextView textView10, TextView textView11,Context context, String days, int number){
        this.schedulesGroup = schedulesGroup;
        this.textView1 = textView1;
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.textView4 = textView4;
        this.textView5 = textView5;
        this.textView6 = textView6;
        this.textView7 = textView7;
        this.textView8 = textView8;
        this.textView9 = textView9;
        this.textView10 = textView10;
        this.textView11 = textView11;
        this.cardView = cardView;
        this.cardView2 = cardView2;
        this.context = context;
        this.days = days;
        this.number = number;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //width = displayMetrics.widthPixels;
        }
        if(schedulesGroup.size()!=0) {
            for (int i = 0; i < schedulesGroup.size(); i++) {
                String temp = schedulesGroup.get(i).numbers;
                int d = Integer.parseInt(temp);
                textView1.setText(number + " пара");
                if (number == d) {
                    names = schedulesGroup.get(i).name;
                    group = schedulesGroup.get(i).group;
                    teacher = schedulesGroup.get(i).teacher;
                    teacher2 = schedulesGroup.get(i).teacher2;
                    study = schedulesGroup.get(i).study;
                    if (schedulesGroup.get(i).fraction.equals("null")) {
                        textView2.setText(names);
                        textView3.setText(teacher);
                        if (teacher2.equals("null")) {

                        } else {
                            textView8.setText(teacher2);
                        }
                        textView4.setText(study);
                        textView10.setText(group);
                        cardView2.getLayoutParams().width = 0;
                        cardView2.requestLayout();
                    } else if (schedulesGroup.get(i).fraction.equals("numerator")) {
                        textView1.setText(number + " пара");
                        textView2.setText(names);
                        textView3.setText(teacher);
                        textView4.setText(study);
                        textView10.setText(group);
                        if (teacher2.equals("null")) {

                        } else {
                            textView8.setText(teacher2);
                        }
                    } else if (schedulesGroup.get(i).fraction.equals("denominator")) {
                        textView1.setText(number + " пара");
                        textView5.setText(names);
                        textView6.setText(teacher);
                        textView7.setText(study);
                        textView11.setText(group);
                        if (teacher2.equals("null")) {

                        } else {
                            textView9.setText(teacher2);
                        }
                    }
                }
            }
        }else {
            textView1.setText(number + " пара");
            cardView2.getLayoutParams().width = 0;
            cardView2.requestLayout();
        }
    }
}
