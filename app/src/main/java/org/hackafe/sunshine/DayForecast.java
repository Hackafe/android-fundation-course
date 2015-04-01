package org.hackafe.sunshine;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Date;


public class DayForecast extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_forecast);

        Intent intent = getIntent();
        String forecast = intent.getStringExtra(Intent.EXTRA_TEXT);
        Long forecastDate =  intent.getLongExtra("TIMESTAMP", 1);
        Date date = new Date(forecastDate * 1000);
        TextView dayForecast = (TextView) findViewById(R.id.dayForecast);
        TextView dayTimestamp = (TextView) findViewById(R.id.dayTimestamp);
        dayForecast.setText(forecast);
        dayTimestamp.setText(date.toString());
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
