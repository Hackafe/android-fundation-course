package org.hackafe.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


/**
* Created by Skayver on 31.3.2015 г..
*/
public class SettingsFragment extends PreferenceFragment {

    private  CheckBoxPreference cCelsius ;
    private CheckBoxPreference cFahrenheit ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        final SharedPreferences.Editor editor = prefs.edit();

        cCelsius =(CheckBoxPreference) findPreference("celsius_key");
        cFahrenheit =(CheckBoxPreference) findPreference("fahrenheit_key");

        cCelsius.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                cFahrenheit.setChecked(!cCelsius.isChecked());
                if(cCelsius.isChecked())
                    editor.putString(getResources().getString(R.string.temp_unit), "Celsius").apply();
                return true;
            }
        });

        cFahrenheit.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                cCelsius.setChecked(!cFahrenheit.isChecked());
                if(cFahrenheit.isChecked())
                    editor.putString(getResources().getString(R.string.temp_unit), "Fahrenheit").apply();
                return true;
            }
        });

    }
}
