package com.example.eddy.servr.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.eddy.servr.R;
import com.example.eddy.servr.ServerConnection;

public class LoadingActivity extends AppCompatActivity {

    public static ServerConnection servr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        connectServer();
        startActivity();
    }

    protected void connectServer(){
        servr = new ServerConnection();
    }

    protected void startActivity() {
        Intent i = new Intent(this, BufferActivity.class);
        startActivity(i);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();

    }
}
