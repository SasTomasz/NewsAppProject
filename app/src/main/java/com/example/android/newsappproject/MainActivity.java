package com.example.android.newsappproject;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class set the main view (recyclerview) and sets content from guardian api
 */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Item>> {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String THE_GUARDIAN_DATA = "https://content.guardianapis.com/search?show-fields=byline%2CbodyText&api-key=e3af7556-fb9b-4676-b253-3798bfc2a8b7";
    private static final int LOADER_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find recycler view where card will be displayed
        recyclerView = findViewById(R.id.recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Item> article = new ArrayList<>();

        // set adapter
        adapter = new RecyclerAdapter(article);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public Loader<List<Item>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Item>> loader, List<Item> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Item>> loader) {

    }
}
