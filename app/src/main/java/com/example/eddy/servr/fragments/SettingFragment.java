package com.example.eddy.servr.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.example.eddy.servr.R;

public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
