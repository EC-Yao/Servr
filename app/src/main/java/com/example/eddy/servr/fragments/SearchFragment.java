package com.example.eddy.servr.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.example.eddy.servr.R;

public class SearchFragment extends Fragment{

//    MaterialSearchView searchView;

    public SearchFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuItem item = menu.findItem(R.id.action_search);
   //     MaterialSearchView searchView = (MaterialSearchView) item.getActionView();
//        searchView.setMenuItem(item);
        return true;

    }
}
