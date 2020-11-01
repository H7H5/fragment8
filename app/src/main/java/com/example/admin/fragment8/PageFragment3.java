package com.example.admin.fragment8;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PageFragment3 extends Fragment {
    private RecyclerView recyclerView;
    private NumbersAdapter adapter;
    View view;
    static ArrayList<Replacement> replacements = new ArrayList<>();
    static final String ARGUMENT_PAGE_NUMBER2 = "arg_page_number";
    static final String ARGUMENT_PAGE_STRING2 = "arg_page_string";
    int pageNumber;
    String d;
    static PageFragment3 newInstance2(int page, ArrayList<Replacement> schedulesGroup1,int day) {
        PageFragment3 pageFragment = new PageFragment3();
        replacements = schedulesGroup1;
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER2, page);
        arguments.putInt(ARGUMENT_PAGE_STRING2, day);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_STRING2);
        d = String.valueOf(pageNumber);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.activity_main5n, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Replacement> replacements1 = new ArrayList<Replacement>();
        for (int i =0; i <replacements.size();i++){
            if(replacements.get(i).day.equals(d)){
                replacements1.add(new Replacement(replacements.get(i).group,replacements.get(i).year,
                        replacements.get(i).month,replacements.get(i).day,replacements.get(i).number,
                        replacements.get(i).old_name,replacements.get(i).old_teacher1,replacements.get(i).old_teacher2,
                        replacements.get(i).old_study,replacements.get(i).new_name,replacements.get(i).new_teacher1,
                        replacements.get(i).new_teacher2,replacements.get(i).new_study));
            }
        }
        adapter = new NumbersAdapter(replacements1.size(),replacements1, getContext(),d);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
