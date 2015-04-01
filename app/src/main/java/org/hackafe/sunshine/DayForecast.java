package org.hackafe.sunshine;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DayForecast extends ActionBarActivity {

    private android.support.v7.widget.ShareActionProvider mShareActionProvider;
    private String data;
    private String timestamp;
    private double dayTemp;
    private String unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_forecast);

        if ((data = getIntent().getStringExtra(ForecastFragment.EXTRA_TEXT)) == null)
            Toast.makeText(getApplicationContext(), "No forecast", Toast.LENGTH_LONG).show();
        if ((timestamp = getIntent().getStringExtra(ForecastFragment.TIMESTAMP)) == null)
            Toast.makeText(getApplicationContext(), "No timestamp", Toast.LENGTH_LONG).show();

        dayTemp = Double.parseDouble(getIntent().getStringExtra(ForecastFragment.DAY_TEMP));

        TextView dataTextView = (TextView) findViewById(R.id.data_tv);
        TextView timestampTextView = (TextView) findViewById(R.id.timestamp_tv);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        unit = sharedPreferences.getString(getResources().getString(R.string.temp_unit), "Celsius");

        if(unit.equals("Celsius"))
            dataTextView.setText(data + " " + dayTemp + " °C");
        else {
              double fTemp = 1.8 * dayTemp + 32;
              dataTextView.setText(data + " " + fTemp + " °F");
             }

         timestampTextView.setText(timestamp);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_forecast, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem menuItem = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = new ShareActionProvider(this);
        MenuItemCompat.setActionProvider(menuItem, mShareActionProvider);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {

            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);

            case R.id.menu_item_share:
                onShareAction();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onShareAction() {
        // Create the share Intent
        String shareDayForecast = "#SunshineApp " + data + " " + timestamp;

        Intent shareIntent = ShareCompat.IntentBuilder.from(this).setType("text/plain").setText(shareDayForecast).getIntent();
        // Set the share Intent
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}