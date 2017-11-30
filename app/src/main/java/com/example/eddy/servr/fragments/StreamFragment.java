package com.example.eddy.servr.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eddy.servr.R;
import com.example.eddy.servr.Activities.ServiceActivityTest;

import java.util.ArrayList;
import java.util.Arrays;

public class StreamFragment extends Fragment{

    public StreamFragment(){}

    @Override @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View stream = inflater.inflate(R.layout.fragment_stream, container, false);
        ArrayList<String> serviceNames = new ArrayList<>(Arrays.asList("Test Case 0",
                "Test Case 1", "Test Case 2", "Test Case 3", "Test Case 4", "Test Case 5",
                "Test Case 6", "Test Case 7", "Test Case 8", "Test Case 9", "Test Case 10",
                "Test Case 11", "Test Case 12", "Test Case 13"));

        if (getActivity() != null) {
            StreamAdapter adapter = new StreamAdapter(serviceNames, getContext());
            ListView listView = stream.findViewById(R.id.StreamListView);
            try {
                listView.setAdapter(adapter);
            } catch (Exception e) {
                Log.e("List View Error", e.getMessage());
            }
        }

        return stream;
    }

    public class StreamAdapter extends BaseAdapter implements ListAdapter {
        private ArrayList<String> list = new ArrayList<>();
        private Context context;

        private StreamAdapter(ArrayList<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int pos) {
            return list.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return 0;
        }

        @SuppressLint("InflateParams")
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                assert inflater != null;
                view = inflater.inflate(R.layout.service_item, null);
            }

            //Handle TextView and display string from your list
            TextView listItemText = view.findViewById(R.id.list_item_string);
            listItemText.setText(list.get(position));
            listItemText.setTextColor(Color.WHITE);

            TextView listItemPrice = view.findViewById(R.id.list_item_price);
            listItemPrice.setText("$Test Price");
            listItemPrice.setTextColor(Color.WHITE);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ServiceActivityTest.class);
                    ServiceActivityTest.toolbarName = list.get(position);
                    startActivity(i);
                }
            });

            return view;
        }
    }
}
