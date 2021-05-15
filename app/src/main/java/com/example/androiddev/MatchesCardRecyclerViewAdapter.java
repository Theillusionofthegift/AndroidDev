package com.example.androiddev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddev.model.Match;
import com.example.androiddev.network.ImageRequester;

import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class MatchesCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchesCardViewHolder> {

    private List<Match> matchesList;
    private ImageRequester imageRequester;

    MatchesCardRecyclerViewAdapter(List<Match> matchesList) {
        this.matchesList = matchesList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public MatchesCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_card, parent, false);
        return new MatchesCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesCardViewHolder holder, int position) {
        if (matchesList != null && position < matchesList.size()) {
            Match m = matchesList.get(position);
            holder.name.setText(m.name);
            holder.setLiked(m.liked);
          imageRequester.setImageFromUrl(holder.matchImage, m.imageUrl);
        }
    }


    @Override
    public int getItemCount () {
        return matchesList.size();
    }
}
