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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    public static final String TAG = "NEWS FRAGMENT";
    private List<NewsItem> newsItems_list;

    public NewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        new NewsLoading().execute();

        return root;

    }


    private class NewsLoading extends AsyncTask<Void, Void, List<NewsItem>> {
        private static final String URL_PATH = "https://www.056.ua/apitest/newstest";

        private Gson gson;
        private URL url;

        public NewsLoading() {
            this.gson = new Gson();
            newsItems_list = new ArrayList<>();
        }

        @Override
        protected List<NewsItem> doInBackground(Void... voids) {
            try {
                url = new URL(URL_PATH);
                JsonObject jsonObject = gson.fromJson(IOUtils.toString(url.openStream()), JsonObject.class);
                Log.d(TAG, "JSON OBJECT " + jsonObject.toString());
                JsonArray jsonArray = jsonObject.getAsJsonArray("response");
                for (JsonElement element : jsonArray) {
                    NewsItem newsItem = gson.fromJson(element.toString(), NewsItem.class);
                    newsItems_list.add(newsItem);
                }
            } catch (JsonSyntaxException | IOException e) {
                Log.d(TAG, "ERROR IN " + e.toString());
            }

            return newsItems_list;
        }


        @Override
        protected void onPostExecute(List<NewsItem> list) {
            // notify adapter about changes
            //  or set adapter here!
            Log.d(TAG, "LIST OF NEWS" + list.size());
        }
    }

}
