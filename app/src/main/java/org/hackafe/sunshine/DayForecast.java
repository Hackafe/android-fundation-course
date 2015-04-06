package org.hackafe.sunshine;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class DayForecast extends ActionBarActivity {
    private ShareActionProvider mShareActionProvider;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_forecast);

        Intent intent = getIntent();
        SimpleDateFormat date = new SimpleDateFormat("MMM dd yyyy", Locale.US);
        TextView txtForDate = (TextView) findViewById(R.id.txtForDate);
        TextView txtDayForecast = (TextView) findViewById(R.id.txtDayForecast);

        txtForDate.setText("Forecast for date " + date.format(intent.getLongExtra("TIMESTAMP", System.currentTimeMillis()) * 1000));
        txtDayForecast.setText(intent.getStringExtra(Intent.EXTRA_TEXT));

        mIntent = new Intent(Intent.ACTION_SEND);
        mIntent.setType("text/plain");
        mIntent.putExtra(Intent.EXTRA_TEXT, "#SunshineApp\n" + intent.getStringExtra(Intent.EXTRA_TEXT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item;
        getMenuInflater().inflate(R.menu.menu_day_forecast, menu);

        item = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        mShareActionProvider.setShareIntent(mIntent);

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
