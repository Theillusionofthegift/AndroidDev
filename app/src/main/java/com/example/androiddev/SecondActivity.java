package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SecondActivity extends AppCompatActivity {

    private FragmentManager manager;
    private String name;
    private String age;
    private String bio;
    private String occ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        manager = getSupportFragmentManager();
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b.containsKey(Constants.KEY_NAME)
                && b.containsKey(Constants.KEY_AGE)
                && b.containsKey(Constants.KEY_BIO)
                && b.containsKey(Constants.KEY_OCC)) {
            name = b.getString(Constants.KEY_NAME);
            age = b.getString(Constants.KEY_AGE);
            bio = b.getString(Constants.KEY_BIO);
            occ = b.getString(Constants.KEY_OCC);
        }


        ProfileFragment fragment = new ProfileFragment();
        Attachment attach = new Attachment(name, age, bio, occ);
        fragment.setAttachment(attach);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragment, "fragA");
        transaction.commit();


    }

    public static class Attachment {
        String name;
        String age;
        String bio;
        String occ;

        Attachment(String name, String age, String bio, String occ) {
            this.name = name;
            this.age = age;
            this.bio = bio;
            this.occ = occ;
        }

    }
}