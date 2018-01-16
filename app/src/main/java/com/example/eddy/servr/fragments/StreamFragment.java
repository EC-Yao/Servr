package com.example.eddy.servr.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.example.eddy.servr.MyAdapter;
import com.example.eddy.servr.R;
import com.example.eddy.servr.ServerConnection;

import java.util.Arrays;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

// November 13th, 2017
// Darren Liu
// Fragment housing all the service boxes  

public class StreamFragment extends Fragment{

    private Button registerButton;
    private CoordinatorLayout coordinatorLayout;
    private PopupWindow mPopupWindow;
    private Context mContext;

    public StreamFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_stream, container, false);

        RecyclerView rv = rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        MyAdapter adapter = new MyAdapter(ServerConnection.streamServices);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        mContext = getActivity().getApplicationContext();
        coordinatorLayout = getActivity().findViewById(R.id.main_layout);


        if (coordinatorLayout ==null){
            System.out.println("hehe");
        }
        else{
            FloatingActionButton fab = rootView.findViewById(R.id.streamFAB);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //inflates the popup xml
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                    assert inflater != null;
                    @SuppressLint("InflateParams") final View customView = inflater.inflate(R.layout.popup_new_service, null);

                    if(customView == null){
                        System.out.println("LOGIN ACTIVITY: customView is null");
                    }else {
                        //initializes the popup window
                        mPopupWindow = new PopupWindow(customView, 650, 1200);

                        //set an elevation value for the popup window
                        mPopupWindow.setElevation(5.0f);

                        //creates the close button
                        ImageButton closeButton = customView.findViewById(R.id.button_close);
                        closeButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                mPopupWindow.dismiss();
                            }
                        });

                        //allows the editTexts to be edited
                        mPopupWindow.setFocusable(true);
                        mPopupWindow.update();

                        //declaring editTexts
                        TextInputEditText title = customView.findViewById(R.id.user_service_title_edit);
                        TextInputEditText price = customView.findViewById(R.id.user_service_price_edit);
                        TextInputEditText description = customView.findViewById(R.id.user_service_description_edit);
                        description.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        description.setRawInputType(InputType.TYPE_CLASS_TEXT);






                        //add error trapping (max amount of characters for title and stuff // price can only have 2 decimals // character limit of description0
                        //more string manipulation! :)





                        //Saving the users submissions
                        Button submit = customView.findViewById(R.id.submit_new_button);

                        //stops the keyboard from opening on startup
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                        //show the pop up window at the center of the layout
                        coordinatorLayout.post(new Runnable() {
                            public void run() {
                                mPopupWindow.showAtLocation(coordinatorLayout, Gravity.CENTER, 0, 0);
                            }
                        });
                    }
                }
            });

        }

        return rootView;
    }
}
