package com.example.den.alenintestcityguide.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.den.alenintestcityguide.model.NewsItem;
import com.example.den.alenintestcityguide.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private static final String URL_PATH = "http://www.056.ua/apitest/newstest";
    private URL url;
    public NewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            url = new URL(URL_PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new NewsLoading().execute(url);
        return inflater.inflate(R.layout.fragment_news, container, false);

    }


    private class NewsLoading extends AsyncTask<URL,Void,NewsItem> {
        private Gson gson;



        public NewsLoading() {
            this.gson = new Gson();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected NewsItem doInBackground(URL... urls) {
            BufferedReader reader = null;
            try {
                String json = IOUtils.toString(url);
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer buffer = new StringBuffer();
                int read;
                char[] chars = new char[1024];
                while ((read = reader.read(chars)) != -1)
                    buffer.append(chars, 0, read);

//                return buffer.toString();
                Log.d("!!!",buffer.toString());
//                return gson.fromJson(IOUtils.toString(urls[0].openStream()), NewsItem.class);
            } catch (JsonSyntaxException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(NewsItem newsItem) {
            super.onPostExecute(newsItem);
//            Log.d("!!!",newsItem.toString());
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }

}
