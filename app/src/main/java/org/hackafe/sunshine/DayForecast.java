package org.hackafe.sunshine;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DayForecast extends ActionBarActivity {

    Intent shareIntent;
    private TextView tvTimestamp, tvDesc;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_forecast);

        long timestamp = getIntent().getLongExtra("TIMESTAMP", 0);
        String desc = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        tvTimestamp = (TextView) findViewById(R.id.timestamp);
        tvDesc = (TextView) findViewById(R.id.description);

        tvTimestamp.setText(String.valueOf(timestamp));
        tvDesc.setText(desc);

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
