package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    private ListView list_view;

    ArrayList<HashMap<String, String>> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = new ArrayList<>();
        list_view = (ListView) findViewById(R.id.list);

        new GetNews().execute();
    }

    private class GetNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // TODO: make a request to the URL
            String url ="https://content.guardianapis.com/search?q=economy&from-date=2018-06-01&order-by=newest&api-key=4f410c1e-1715-4dd5-b225-40b71d895d84";
            String jsonString = "";
            try {
                jsonString = sh.makeHttpRequest(createUrl(url));
            } catch (IOException e) {
                return null;
            }

            Log.e(TAG, "Response from url: " + jsonString);
            if (jsonString != null) {
                try {

                    //TODO: Create a new JSONObject
                    JSONObject jsonObj = new JSONObject(jsonString);

                    // TODO: Get the JSON Array node
                    JSONObject results = jsonObj.getJSONObject("response");
                    JSONArray news = results.getJSONArray("results");

                    // looping through all news
                    for (int i = 0; i < news.length(); i++) {
                        //TODO: get the JSONObject
                        JSONObject c = news.getJSONObject(i);
                        String  webUrl = c.getString("webUrl");
                        String  webTitle = c.getString("webTitle");


                        // tmp hash map for a single news
                        HashMap<String, String> eachNews= new HashMap<>();

                        // add each child node to HashMap key => value
                        eachNews.put("webTitle", webTitle);
                        eachNews.put("webUrl", webUrl);

                        // adding a news to our news list
                        newsList.add(eachNews);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                return null;
            }
            return url;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, newsList,
                    R.layout.list_item, new String[]{"webTitle", "webUrl"},
                    new int[]{R.id.webTitle, R.id.webUrl});
            list_view.setAdapter(adapter);
        }
    }
}
