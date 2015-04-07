package org.hackafe.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    SharedPreferences mSharedPreferences;
    String prefLocation, prefUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        prefLocation = mSharedPreferences.getString("pref_location", "");
        prefUnits = mSharedPreferences.getString("pref_units", "Metric");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new
                ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        prefLocation = mSharedPreferences.getString("pref_location", "");
        prefUnits = mSharedPreferences.getString("pref_units", "Metric");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!prefLocation.equals(mSharedPreferences.getString("pref_location", "")) ||
                !prefUnits.equals(mSharedPreferences.getString("pref_units", "Metric"))) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }
}
