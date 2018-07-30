package com.example.android.newsappproject;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class set the main view (recyclerview) and sets content from guardian api
 */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Item>> {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private String apiKey = BuildConfig.THE_GUARDIAN_API_KEY;
    private String THE_GUARDIAN_DATA = "https://content.guardianapis.com/search?show-fields=byline%2CbodyText&api-key=" + apiKey;
    private static final int LOADER_ID = 0;
    private TextView emptyStateView;
    private View loadingIndicator;
    private NetworkInfo networkInfo;

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find recycler view where card will be displayed
        recyclerView = findViewById(R.id.recycler_view);

        // find loadingIndicator view
        loadingIndicator = findViewById(R.id.indicator);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Find emptyStateView that is displayed when there is no data
        emptyStateView = findViewById(R.id.empty_state_view);

        List<Item> article = new ArrayList<>();

        // set adapter
        adapter = new RecyclerAdapter(article);
        recyclerView.setAdapter(adapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            getLoaderManager().initLoader(LOADER_ID, null, this);

        } else {
            loadingIndicator.setVisibility(View.GONE);
            emptyStateView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<Item>> onCreateLoader(int id, Bundle args) {
        // create new loader for the given url
        return new ArticleLoader(this, THE_GUARDIAN_DATA);
    }

    @Override
    public void onLoadFinished(Loader<List<Item>> loader, List<Item> data) {

        // Hide loading indicator because the data has been loaded
        loadingIndicator.setVisibility(View.GONE);
        emptyStateView.setText("");

        if (data != null && !data.isEmpty()) {
            adapter = new RecyclerAdapter(data);
            recyclerView.setAdapter(adapter);
        } else {
            adapter = new RecyclerAdapter(data);
            recyclerView.setAdapter(adapter);
            //set empty state "There is no data"
            emptyStateView.setText(R.string.no_data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Item>> loader) {
        adapter = null;

    }

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
