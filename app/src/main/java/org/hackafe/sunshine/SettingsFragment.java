package org.hackafe.sunshine;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by admin on 8.4.2015 г..
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
