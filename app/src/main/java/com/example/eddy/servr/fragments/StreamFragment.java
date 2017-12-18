package com.example.eddy.servr.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.eddy.servr.Activities.MyAdapter;
import com.example.eddy.servr.R;

public class StreamFragment extends Fragment{

    public StreamFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);

        RecyclerView rv = rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        MyAdapter adapter = new MyAdapter(new String[]{"Test Case 0",
                  "Test Case 1", "Test Case 2", "Test Case 3", "Test Case 4", "Test Case 5",
                  "Test Case 6", "Test Case 7", "Test Case 8", "Test Case 9", "Test Case 10",
                  "Test Case 11", "Test Case 12", "Test Case 13"});
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }
}
