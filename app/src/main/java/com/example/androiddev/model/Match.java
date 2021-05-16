package com.example.androiddev.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Match implements Parcelable {
    public String uid;
    public String name;
    public boolean liked;
    public String imageUrl;

    public Match() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Match(String name, boolean liked, String imageUrl) {
        this.name = name;
        this.liked = liked;
        this.imageUrl = imageUrl;
    }

    public Match(Parcel in) {
        name = in.readString();
        liked = in.readByte() != 0;
        imageUrl = in.readString();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("liked", liked);
        result.put("imageUrl", imageUrl);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (liked ? 1 : 0));
        dest.writeString(imageUrl);
    }
}