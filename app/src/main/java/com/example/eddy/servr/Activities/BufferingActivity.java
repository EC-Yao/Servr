package com.example.eddy.servr.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;

import com.example.eddy.servr.R;
import com.example.eddy.servr.ServerConnection;

// November 30th, 2017
// Darren Liu
// Loading screen for the application

public class BufferingActivity extends AppCompatActivity {

    public static ServerConnection servr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

        //connectServer();

        //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


        startActivity();
    }

    //Establishes connection with the server
    protected void connectServer(){
        servr = new ServerConnection();
    }

    protected void startActivity() {
        Intent i = new Intent(this, LoaderActivity.class);
        startActivity(i);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();

    }
}
