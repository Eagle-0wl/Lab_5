package com.example.lab_5;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataLoader extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(Constants.DATA_LOADER_TAG, "method doInBackground called");
        Parser.parsing ();
        return null;
    }
    public static InputStream downlowdUrl(String urlString) throws IOException {
        Log.d(Constants.DATA_LOADER_TAG, "method downlowdUrl called");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(1000);
        conn.setConnectTimeout(1000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

}
