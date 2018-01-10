package com.example.eddy.servr.fragments;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import com.example.eddy.servr.R;

// January 2nd, 2018
// Darren Liu
//

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

}
