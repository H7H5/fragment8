package com.example.admin.fragment8;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class ReplTask {
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
    String group;
    String year;
    String month;
    String day;
    String number;
    String old_name;
    String old_teacher1;
    String old_teacher2;
    String old_study;
    String new_name;
    String new_teacher1;
    String new_teacher2;
    String new_study;
    int width = 1;
    Replacement replacement;
    public ReplTask(Replacement replacement, androidx.cardview.widget.CardView cardView2,
                    androidx.cardview.widget.CardView cardView, TextView textView1, TextView textView2,
                    TextView textView3, TextView textView4,
                    TextView textView5, TextView textView6, TextView textView7,
                    TextView textView8, TextView textView9,
                    TextView textView10, TextView textView11,Context context){
        this.replacement = replacement;
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
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            width = displayMetrics.widthPixels;
        }
        if(replacement !=null) {
                number = replacement.number;
                textView1.setText(replacement.group+" група "+ number + " пара");
            //System.out.println(replacement.old_teacher2);
                    //} else if (schedulesGroup.get(i).fraction.equals("numerator")) {
                        //textView1.setText(number + " пара");
                        textView2.setText(replacement.old_name);
                        textView3.setText(replacement.old_teacher1);
                        textView4.setText(replacement.old_study);
                        //textView10.setText(replacement.group);
                        //if (old_teacher2.equals("null")) {

                        //} else {
                           textView8.setText(replacement.old_teacher2);
                        //}
                    //} else if (schedulesGroup.get(i).fraction.equals("denominator")) {
                        //textView1.setText(number + " пара");
                        textView5.setText(replacement.new_name);
                        textView6.setText(replacement.new_teacher1);
                        textView7.setText(replacement.new_study);
                        //textView11.setText(replacement.group);
                        //if (new_teacher2.equals("null")) {

                        //} else {
                            textView9.setText(replacement.new_teacher2);
                        //}

        }else {
            //textView1.setText(number + " пара");
            //cardView2.getLayoutParams().width = 0;
            //cardView2.requestLayout();
        }

    }
}
