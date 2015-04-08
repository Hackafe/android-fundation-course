package org.hackafe.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by admin on 8.4.2015 г..
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String CELSIUS = "Celsius";
    public static final String FAHRENHEIT = "Fahrenheit";
    public static final String KEY_PREF_TEMP_UNITS = "pref_tempUnits";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_PREF_TEMP_UNITS)) {
            Preference temperaturePref = findPreference(key);
            // Set summary to be the user-description for the selected value
            temperaturePref.setSummary(sharedPreferences.getString(key, ""));
        }
    }
}
