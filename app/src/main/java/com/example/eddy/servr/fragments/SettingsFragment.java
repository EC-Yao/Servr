package com.example.eddy.servr.fragments;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import com.example.eddy.servr.R;

/** January 2, 2018
 *  Darren Liu
 *
 *      Allow the user to changes their password, emails and other user features.
 */

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        // Displays user settings
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

}
