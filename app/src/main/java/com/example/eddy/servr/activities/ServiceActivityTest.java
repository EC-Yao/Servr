package com.example.eddy.servr.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.eddy.servr.R;

public class ServiceActivityTest extends AppCompatActivity {

    Toolbar toolbar;
    public static String toolbarName = "Default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_test_activity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()==null){
            Log.e("Error", "Null SupportActionBar");
        }
        else {
            getSupportActionBar().setTitle(toolbarName);
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Snackbar.make(view, "Fix the title isnt changing", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
            updateObject("Lawn Mowin'", "I should watch Elsa and Spiderman! " +
                    "It is a friendly and interactive youtube series." +
                    " Don't forget to like and subscribble");
            }
        });
    }

    //launch this when creating
    public void updateObject(String title, String bodyText){

        if(getSupportActionBar()==null){
            Log.e("Error", "Null SupportActionBar");
        }
        else {
            getSupportActionBar().setTitle(title);
        }
        System.out.println(toolbar.getTitle());

        TextView bodyTextView = findViewById(R.id.serviceLargeTextView);
        bodyTextView.setText(bodyText);

    }
}
