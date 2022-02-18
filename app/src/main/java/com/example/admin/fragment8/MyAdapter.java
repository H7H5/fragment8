package com.example.admin.fragment8;

import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<Lesson> schedulesGroup = new ArrayList<>();
    ArrayList<Lesson> schedulesGroup1 = new ArrayList<>();
    String teacher;
    private Context context;
    String days;
    int d;
    public MyAdapter(String teacher, Context context,String days,int d,ArrayList<Lesson> schedulesGroup ) {
        this.schedulesGroup = schedulesGroup;
        this.teacher = teacher;
        this.context = context;
        this.days = days;
        this.d = d;
        for (int i =0; i <schedulesGroup.size();i++){
            if(schedulesGroup.get(i).day.equals(days)){
                schedulesGroup1.add(new Lesson(schedulesGroup.get(i).name,
                        schedulesGroup.get(i).group,
                        schedulesGroup.get(i).teacher,
                        schedulesGroup.get(i).teacher2,
                        schedulesGroup.get(i).day,
                        schedulesGroup.get(i).study,
                        schedulesGroup.get(i).numbers,
                        schedulesGroup.get(i).fraction));
            }
        }

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item2, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TestTask testTask = new TestTask(schedulesGroup1,viewHolder.cardView2,viewHolder.cardView,viewHolder.textView1,viewHolder.textView2,
                viewHolder.textView3,viewHolder.textView4,viewHolder.textView5,viewHolder.textView6,viewHolder.textView7,
                viewHolder.textView8,viewHolder.textView9,viewHolder.textView10,viewHolder.textView11,context,days,i+1);

    }
    @Override
    public int getItemCount() {
        return 4;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        androidx.cardview.widget.CardView cardView;
        androidx.cardview.widget.CardView cardView2;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView8;

        public TextView textView5;
        public TextView textView6;
        public TextView textView7;
        public TextView textView9;
        public TextView textView10;
        public TextView textView11;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.number);
            textView2 = (TextView) itemView.findViewById(R.id.name);
            textView3 = (TextView) itemView.findViewById(R.id.teacher);
            textView4 = (TextView) itemView.findViewById(R.id.study);
            textView8 = (TextView) itemView.findViewById(R.id.teacher2);

            textView5 = (TextView) itemView.findViewById(R.id.name1);
            textView6 = (TextView) itemView.findViewById(R.id.teacher1);
            textView9 = (TextView) itemView.findViewById(R.id.teacher12);
            textView7 = (TextView) itemView.findViewById(R.id.study1);
            textView10 = (TextView) itemView.findViewById(R.id.group1);
            textView11 = (TextView) itemView.findViewById(R.id.group2);
            cardView = (androidx.cardview.widget.CardView)itemView.findViewById(R.id.cardView2);
            cardView2 = (androidx.cardview.widget.CardView)itemView.findViewById(R.id.cardView3);

        }
    }
}