package com.example.eddy.servr.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.eddy.servr.R;

import java.util.ArrayList;

// November 20th, 2017 :) bday
// Darren Liu
// An activity displaying specific information about the clicked service item

public class ServiceItemActivity extends AppCompatActivity {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private CoordinatorLayout mCoordinatorLayout;

    Toolbar toolbar;
    public static String toolbarName = "Default";
    public static String description = "Default";
    public static String price = "$0.00";
    public static ArrayList<String> user;

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
        mContext = getApplicationContext();
        mCoordinatorLayout = findViewById(R.id.coordinator_layout);

        FloatingActionButton fab = findViewById(R.id.ServiceFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                assert inflater != null;
                @SuppressLint("InflateParams") final View customView = inflater.inflate(R.layout.popup_user, null);

                if(customView == null){
                    System.out.println("LOGIN ACTIVITY: customView is null");
                }else{
                    //initializes the popup window
                    mPopupWindow = new PopupWindow(customView, 650, 1200);

                    //set an elevation value for the popup window
                    mPopupWindow.setElevation(5.0f);

                    TextView userName = customView.findViewById(R.id.user_name);
                    TextView userInfo = customView.findViewById(R.id.user_info);

                    userName.setText(user.get(1));
                    userInfo.setText(user.get(3) + "\n" + user.get(4) + "\n" +
                        user.get(5) + ", " + user.get(6).replaceAll("]", ""));

                    //creates the close button
                    ImageButton closeButton = customView.findViewById(R.id.close_button);
                    closeButton.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View view){
                            mPopupWindow.dismiss();
                        }
                    });

                    //allows the editTexts to be edited
                    mPopupWindow.setFocusable(true);
                    mPopupWindow.update();

                    //stops the keyboard from opening on startup
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                    //show the pop up window at the center of the layout
                    mCoordinatorLayout.post(new Runnable() {
                        public void run() {
                            mPopupWindow.showAtLocation(mCoordinatorLayout, Gravity.CENTER, 0, 0);
                        }
                    });
                }

            }
        });

        updateText();
    }

    //Updates the text of the activity
    public void updateText(){
        TextView bodyTextView = findViewById(R.id.serviceLargeTextView);

        bodyTextView.setText(description + "\n\nPrice: " + price);
    }
}
