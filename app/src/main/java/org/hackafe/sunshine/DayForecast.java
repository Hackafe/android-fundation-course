package org.hackafe.sunshine;

import android.support.v7.app.ActionBarActivity;
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
    //private String unit;


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

        dataTextView.setText(data + " " + dayTemp + " °C");
        timestampTextView.setText(timestamp);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
