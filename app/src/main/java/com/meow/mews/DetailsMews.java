package com.meow.mews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.meow.mews.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsMews extends AppCompatActivity {

    NewsHeadlines headlines;
    TextView title, author, time, details, content;
    ImageView imgnews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_mews);

        title = findViewById(R.id.detailstitletext);
        author = findViewById(R.id.detailsauthor);
        time = findViewById(R.id.detailstime);
        details = findViewById(R.id.detailsdetails);
        content = findViewById(R.id.detailscontent);
        imgnews = findViewById(R.id.imgdetails);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        title.setText(headlines.getTitle());
        author.setText(headlines.getAuthor());
        time.setText(headlines.getPublishedAt());
        details.setText(headlines.getDescription());
        content.setText(headlines.getContent());
        if (headlines.getUrlToImage() != null) Picasso.get().load(headlines.getUrlToImage()).into(imgnews);
    }
}