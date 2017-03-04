package com.example.den.alenintestcityguide;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private URL url;
    private HttpURLConnection connection;

    public NewsFragment() {
        try {
            url = new URL("http://www.056.ua/apitest/newstest");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            Log.d("!!!",connection.getResponseMessage().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_news, container, false);

    }

}
