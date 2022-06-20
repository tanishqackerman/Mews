package com.meow.mews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.meow.mews.Models.NewsAPIResponse;
import com.meow.mews.Models.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    MeowAdapter meowAdapter;
    ProgressDialog dialog;
    Button business, entertainment, general, science, health, sports, technology;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        business = findViewById(R.id.business);
        entertainment = findViewById(R.id.entertainment);
        general = findViewById(R.id.general);
        science = findViewById(R.id.science);
        health = findViewById(R.id.health);
        sports = findViewById(R.id.sports);
        technology = findViewById(R.id.technology);
        searchView = findViewById(R.id.querysearch);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching News Articles...");
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);

        business.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        general.setOnClickListener(this);
        science.setOnClickListener(this);
        health.setOnClickListener(this);
        sports.setOnClickListener(this);
        technology.setOnClickListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching News Articles of " + query);
                dialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener<NewsAPIResponse> listener = new OnFetchDataListener<NewsAPIResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()) Toast.makeText(MainActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An Error Occurred!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        meowAdapter = new MeowAdapter(this, list, this);
        recyclerView.setAdapter(meowAdapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailsMews.class).putExtra("data", headlines));
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        dialog.setTitle("Fetching News Articles of " + category);
        dialog.show();
        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener, category, null);
    }
}