package com.example.android.newsappproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * This class help get data from internet
 */
public class QueryUtils {
    private static final String TAG = QueryUtils.class.getSimpleName();

    // default constructor
    public QueryUtils() {
    }

    public static List<Item> fetchArticle(String urlAddress) {
        URL url = createUrl(urlAddress);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "Error closing input stream", e);
        }

        // Create an empty ArrayList that we can start adding elements of article
        List<Item> articles = new ArrayList<>();

        try {
            JSONObject rootJson = new JSONObject(jsonResponse);
            JSONObject jsonObject = rootJson.getJSONObject("response");

            JSONArray jsonArrayResults = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArrayResults.length(); i++) {
                JSONObject jsonResults = jsonArrayResults.getJSONObject(i);
                JSONObject jsonFields = jsonResults.getJSONObject("fields");

                String text = jsonFields.getString("bodyText");
                String title = jsonResults.getString("webTitle");
                String section = jsonResults.getString("sectionName");
                String author = jsonFields.getString("byline");
                String publishDate = jsonResults.getString("webPublicationDate");
                String web = jsonResults.getString("webUrl");

                Log.i(TAG, "fetchArticle after extract json, webUrl: " + web);

                articles.add(new Item(title, author, publishDate, text, section, web));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return articles;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving the Guardian JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}
