package com.example.eddy.servr.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddy.servr.R;

/**
 * Created by darli on 2017-11-15.
 */

public class Fragment_1 extends Fragment{

    public Fragment_1(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_1, container, false);
        return fragment;
    }
}
