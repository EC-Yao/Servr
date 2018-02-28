package com.example.eddy.servr.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eddy.servr.R;
import com.example.eddy.servr.ServerConnection;

/**
 *  November 30th, 2017
 *  Eddy Yao
 *
 *      Launch activity for the application - This activity will initiate everything required and
 * essentially manage any background tasks that need to be done before the app starts
 */


public class BufferingActivity extends AppCompatActivity {

    // Public server connection to be used across the project
    public static ServerConnection servr;

    // Instancing UI and setting connection to server
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        connectServer();

        startActivity();
    }

    //Establishes connection with the server
    protected void connectServer(){
        servr = new ServerConnection();
    }

    // Launches loading screen
    protected void startActivity() {
        Intent i = new Intent(this, LoaderActivity.class);
        startActivity(i);
    }
}
