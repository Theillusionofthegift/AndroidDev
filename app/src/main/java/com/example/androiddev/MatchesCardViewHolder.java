package com.example.androiddev;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

public class MatchesCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView matchImage;
    public TextView name;

    public MatchesCardViewHolder(@NonNull View itemView) {
        super(itemView);
        matchImage = itemView.findViewById(R.id.match_image);
        name = itemView.findViewById(R.id.match_name);
        CheckBox likeBtn = (CheckBox) itemView.findViewById(R.id.like_button);
        likeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    MyListener myListener = (MyListener) itemView.getContext();
                    if (myListener != null) {
                        myListener.matchesLikeToast(name.getText().toString());
                    }
                }
            }
        });
    }


}
