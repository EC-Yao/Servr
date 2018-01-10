package com.example.eddy.servr.Activities;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eddy.servr.R;
import com.example.eddy.servr.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        //ensures the settings are initialized with their default values
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

}

    /*

    Something about saving settings

    https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/en/Unit%204/92_p_adding_settings_to_an_app.html

     */
