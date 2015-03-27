package org.hackafe.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import static android.support.v4.view.MenuItemCompat.*;


public class DayForecast extends ActionBarActivity {

    TextView forecast_text, click;
    private ShareActionProvider myShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_forecast);

        Intent intent = getIntent();
        String forecast = intent.getStringExtra(Intent.EXTRA_TEXT);
        String date = intent.getStringExtra("TIMESTAMP");

        forecast_text = (TextView) findViewById(R.id.forecast_text);
        forecast_text.setText(forecast);

        click = (TextView) findViewById(R.id.click_date);
        click.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_day_forecast, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        myShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        setShareIntent();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Set the share intent
    private void setShareIntent(){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_TEXT,forecast_text.getText() + "  " + "#SunshineApp");

        myShareActionProvider.setShareIntent(intent);
    }

}


