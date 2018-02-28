package com.example.eddy.servr.Activities;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eddy.servr.R;
import com.example.eddy.servr.fragments.SettingsFragment;

/** January 2, 2017
 *  Darren Liu
 *
 *      Launches the SettingsFragment
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Replaces the Settings activity launched with the SettingsFragment
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        //ensures the settings are initialized with their default values
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

}