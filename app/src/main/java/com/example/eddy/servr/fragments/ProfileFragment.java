package com.example.eddy.servr.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eddy.servr.R;
import com.example.eddy.servr.ServerConnection;

import org.w3c.dom.Text;

import java.util.ArrayList;

/** November 27th, 2017
 *  Eddy Yao
 *
 *      Displays currently logged in users information
 */


public class ProfileFragment extends Fragment{

    private View rootView;

    public ProfileFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        TextView username = rootView.findViewById(R.id.username);
        TextView email = rootView.findViewById(R.id.email);
        TextView phone = rootView.findViewById(R.id.phone);
        TextView location = rootView.findViewById(R.id.location);

        username.setText(ServerConnection.user.get(1));
        email.setText(ServerConnection.user.get(3));
        phone.setText(ServerConnection.user.get(4));
        location.setText(String.format("%s, %s", ServerConnection.user.get(5), ServerConnection.user.get(6)));
    }
}
