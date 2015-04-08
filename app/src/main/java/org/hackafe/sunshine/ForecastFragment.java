package org.hackafe.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    private ListView collection;
    ForecastAdapter adapter;

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Sunshine", "----------------------------------------------------");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        collection = (ListView) rootView.findViewById(R.id.container);


        final EditText countInput = (EditText)rootView.findViewById(R.id.countInput);


        Button addMoreBtn = (Button) rootView.findViewById(R.id.btn_add_more_items);




        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        collection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Forecast item = (Forecast) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), DayForecast.class);
                intent.putExtra("timestamp", item.timestamp);
                intent.putExtra("desc", item.desc);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        new GetForecastDataTask().execute();
    }

    class GetForecastDataTask extends AsyncTask<Void, Void, List<Forecast>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Forecast> doInBackground(Void... params) {
            List<Forecast> forecast = parseForecast(getForecast());
            return forecast;
        }

        @Override
        protected void onPostExecute(List<Forecast> forecast) {
            super.onPostExecute(forecast);
            adapter = new ForecastAdapter(LayoutInflater.from(getActivity()), forecast);
            collection.setAdapter(adapter);
        }
    }

    private List<Forecast> parseForecast(String data) {
        try {
            List<Forecast> forecastList = new ArrayList<>();
            // parse String so we have JSONObject
            JSONObject obj = new JSONObject(data);
            // get "list" field as array
            JSONArray list = obj.getJSONArray("list");
            // iterate array and get forecast
            for (int i=0; i<list.length(); i++) {
                // get "i"th forecast
                JSONObject forecastObj = list.getJSONObject(i);

                // get "temp" object
                JSONObject temp = forecastObj.getJSONObject("temp");
                // extract "day" temperature
                double dayTemp = temp.getDouble("day");

                // get "weather" array
                JSONArray weather = forecastObj.getJSONArray("weather");
                // get 1st weather
                JSONObject weather1 = weather.getJSONObject(0);

                // extract "description" from 1st weather
                String description = weather1.getString("description");

                // extract "dt" date time in unix epoch format
                long dt = forecastObj.getLong("dt");
                String dateStr = SimpleDateFormat.getDateInstance().format(new Date(dt*1000));

                Forecast forecast = new Forecast();
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

                String tempUnits = sharedPrefs.getString(SettingsActivity.KEY_PREF_TEMP_UNITS, "");
                if (tempUnits.equals(SettingsActivity.FAHRENHEIT)) {
                    dayTemp = (dayTemp * 1.8) + 32;
                    forecast.desc = String.format("%s - %s  %.1f°F", dateStr, description, dayTemp);
                } else {
                    forecast.desc = String.format("%s - %s  %.1f°C", dateStr, description, dayTemp);
                }
                forecast.timestamp = dt;
                forecastList.add(forecast);
                Log.d("Sunshine", "forecast = "+forecast);
            }
            return forecastList;
        } catch (Throwable t) {
            Log.e("Sunshine", t.getMessage(), t);
            return null;
        }
    }

    private String getForecast() {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=Plovdiv&mode=json&units=metric&cnt=7");
            InputStream inputStream = url.openStream();
            try {
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }

                return total.toString();
            } finally {
                inputStream.close();
            }
        } catch (Throwable t) {
            Log.e("Sunshine", t.getMessage(), t);
            return null;
        }
    }
}
