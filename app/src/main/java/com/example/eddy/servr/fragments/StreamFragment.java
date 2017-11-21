package com.example.eddy.servr.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eddy.servr.R;

import java.util.ArrayList;
import java.util.Arrays;

public class StreamFragment extends Fragment{

    public StreamFragment(){}
    Activity mActivity;

    @Override @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View stream = inflater.inflate(R.layout.fragment_stream, container, false);
        ArrayList<String> serviceNames = new ArrayList<>(Arrays.asList("Test Case 0",
                "Test Case 1", "Test Case 2", "Test Case 3", "Test Case 4", "Test Case 5",
                "Test Case 6", "Test Case 7", "Test Case 8", "Test Case 9", "Test Case 10",
                "Test Case 11", "Test Case 12", "Test Case 13"));

        if (getActivity() != null) {
            ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.service_item, serviceNames);
            ListView listView = stream.findViewById(R.id.StreamListView);
            try {
                listView.setAdapter(adapter);
            } catch (Exception e) {
                Log.e("List View Error", e.getMessage());
            }
        }

        return stream;
    }
}
