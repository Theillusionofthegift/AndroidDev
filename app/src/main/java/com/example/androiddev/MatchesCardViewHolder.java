package com.example.androiddev;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

public class MatchesCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView productImage;
    public TextView name;
    public TextView bio;

    public MatchesCardViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        name = itemView.findViewById(R.id.match_name);
        bio = itemView.findViewById(R.id.match_description);
    }
}
