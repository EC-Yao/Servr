package com.example.eddy.servr.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.eddy.servr.R;

// November 20th, 2017 :) bday
// Darren Liu
// An activity displaying specific information about the clicked service item

public class ServiceItemActivity extends AppCompatActivity {

    Toolbar toolbar;
    public static String toolbarName = "Default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_item);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()==null){
            Log.e("Error", "Null SupportActionBar");
        }
        else {
            getSupportActionBar().setTitle(toolbarName);
        }

        //Sets up floating action button
        FloatingActionButton fab = findViewById(R.id.ServiceFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            Snackbar.make(view, "Wow!", Snackbar.LENGTH_SHORT)
                   .setAction("Action", null).show();

            updateBodyText( "It's beginning to feel like a lot Christmas," +
                    "Everywhere you go;" +
                    "Take a look at the five and ten," +
                    "It's glistening once again," +
                    "With candy canes and silver lanes aglow!");
            }
        });
    }

    //Updates the text of the activity
    public void updateBodyText(String bodyText){
        TextView bodyTextView = findViewById(R.id.serviceLargeTextView);
        bodyTextView.setText(bodyText);
    }
}
