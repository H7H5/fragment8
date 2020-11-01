package com.example.admin.fragment8;

import java.util.ArrayList;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PageFragment2 extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    String days = "Monday";
    View view;
    ListView lvMain;
    static ArrayList<Lesson> schedulesGroup = new ArrayList<>();
    static String my_new_str2;
    static final String ARGUMENT_PAGE_NUMBER2 = "arg_page_number";
    static final String ARGUMENT_PAGE_STRING2 = "arg_page_string";
    int pageNumber;
    static  int d1;
    static PageFragment2 newInstance2(int page,int day ,int d, ArrayList<Lesson> schedulesGroup1) {
        PageFragment2 pageFragment = new PageFragment2();
        schedulesGroup = schedulesGroup1;
        Bundle arguments = new Bundle();
        d1 =d;
        arguments.putInt(ARGUMENT_PAGE_NUMBER2, page);
        arguments.putInt(ARGUMENT_PAGE_STRING2, day);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_STRING2);
        switch (pageNumber){
            case 10:
                days = "Monday";
                break;
            case 11:
                days = "Tuesday";
                break;
            case 12:
                days = "Wednesday";
                break;
            case 13:
                days = "Thursday";
                break;
            case 14:
                days = "Friday";
                break;
            case 15:
                days = "Saturday";
                break;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.activity_main5n, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter(my_new_str2, getContext(),days,d1,schedulesGroup);
        recyclerView.setAdapter(adapter);
        return view;
    }


}