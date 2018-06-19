package fanos.com.lole.fragments;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import fanos.com.lole.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_lole);
    }
}
