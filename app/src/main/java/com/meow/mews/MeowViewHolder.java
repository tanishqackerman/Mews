package com.meow.mews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MeowViewHolder extends RecyclerView.ViewHolder {

    TextView titletext, sourcetext;
    ImageView imgheadline;
    CardView cardview;

    public MeowViewHolder(@NonNull View itemView) {
        super(itemView);

        titletext = itemView.findViewById(R.id.titletext);
        sourcetext = itemView.findViewById(R.id.sourcetext);
        imgheadline = itemView.findViewById(R.id.imageheadline);
        cardview = itemView.findViewById(R.id.cardview);
    }
}
