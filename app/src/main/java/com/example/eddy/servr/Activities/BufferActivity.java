package com.example.eddy.servr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eddy.servr.R;

public class BufferActivity extends AppCompatActivity {
private TextView tv ;
private ImageView iv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);
        tv = findViewById(R.id.bufferText);
        iv = findViewById(R.id.bufferImage);
        Animation loadingAnim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        tv.startAnimation(loadingAnim);
        iv.startAnimation(loadingAnim);
        final Intent i = new Intent(this, LoginActivity.class);
        Thread timer = new Thread(){
            public void run (){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
            timer.start();
    }

}
