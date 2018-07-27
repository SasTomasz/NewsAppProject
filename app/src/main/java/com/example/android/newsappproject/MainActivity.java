package com.example.android.newsappproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This class set the main view (recyclerview) and sets content from guardian api
 */

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find recycler view where card will be displayed
        recyclerView = findViewById(R.id.recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Item> article = new ArrayList<>();
        article.add(new Item("Example Title", "Tomasz Sas", "23-07-2018 13:09", "To help locate parents who were deported to Central America, including those who do not know where in the US their children are, the child advocacy group Kids in Need of Defense (Kind) on Thursday announced a special transnational reunification program that would also provide affected families with legal and psychosocial services. Attorneys are also worried about what happens to reunited families, who are either held in family detention or released as they wait for their immigration case to be tried. ", "Politics"));
        article.add(new Item("Example Title", "Tomasz Sas", "23-07-2018 13:09", "To help locate parents who were deported to Central America, including those who do not know where in the US their children are, the child advocacy group Kids in Need of Defense (Kind) on Thursday announced a special transnational reunification program that would also provide affected families with legal and psychosocial services. Attorneys are also worried about what happens to reunited families, who are either held in family detention or released as they wait for their immigration case to be tried. ", "Politics"));
        article.add(new Item("Example Title", "Tomasz Sas", "23-07-2018 13:09", "To help locate parents who were deported to Central America, including those who do not know where in the US their children are, the child advocacy group Kids in Need of Defense (Kind) on Thursday announced a special transnational reunification program that would also provide affected families with legal and psychosocial services. Attorneys are also worried about what happens to reunited families, who are either held in family detention or released as they wait for their immigration case to be tried. ", "Politics"));

        // set adapter
        adapter = new RecyclerAdapter(article);
        recyclerView.setAdapter(adapter);
    }
}
