package com.example.admin.fragment8;



import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.ViewHolder> {
    private int numberItems;
    ArrayList<Replacement> replacements = new ArrayList<Replacement>();
    Context context;
    String day;
    public NumbersAdapter(int viewHolderCount,ArrayList<Replacement> replacements, Context context,String da ) {
        this.numberItems = viewHolderCount;
        this.replacements = replacements;
        this.context = context;
        day = String.valueOf(da);
        System.out.println(day+" day ");
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item3, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ReplTask replTask = new ReplTask(replacements.get(i),viewHolder.cardView2,viewHolder.cardView,viewHolder.textView1,viewHolder.textView2,
                viewHolder.textView3,viewHolder.textView4,viewHolder.textView5,viewHolder.textView6,viewHolder.textView7,
                viewHolder.textView8,viewHolder.textView9,viewHolder.textView10,viewHolder.textView11,context);

    }
    @Override
    public int getItemCount() {
        return numberItems;
    }
    /*
    class  NumberViewHolder extends RecyclerView.ViewHolder{
        TextView listItemNumberView;
        public NumberViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
        }
        void bind(int s){
            listItemNumberView.setText(String.valueOf(s));
        }
    }
     */
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
