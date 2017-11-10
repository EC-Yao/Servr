package com.example.eddy.servr.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eddy.servr.R;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startActivity();
    }

    protected void startActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    protected void onPause() {
        super.onPause();

    }

    protected void onResume() {
        super.onResume();

    }
}
