package com.meow.mews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meow.mews.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MeowAdapter extends RecyclerView.Adapter<MeowViewHolder> {

    private Context context;
    private List<NewsHeadlines> headlines;
    private SelectListener listener;

    public MeowAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MeowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeowViewHolder(LayoutInflater.from(context).inflate(R.layout.meownews, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeowViewHolder holder, int position) {
        holder.titletext.setText(headlines.get(position).getTitle());
        holder.sourcetext.setText(headlines.get(position).getSource().getName());
        if (headlines.get(position).getUrlToImage() != null) Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.imgheadline);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(headlines.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
