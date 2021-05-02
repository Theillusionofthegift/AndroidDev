package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new Toolbar.OnMenuItemClickListener(MenuItem) {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.nav_profile:
                        ProfileFragment frag1 = new ProfileFragment();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.container, frag1, "profile");
                        transaction.commit();
                        break;
                    case R.id.nav_matches:
                        MatchesFragment frag2 = new MatchesFragment();
                        FragmentTransaction transaction2 = manager.beginTransaction();
                        transaction2.replace(R.id.container, frag2, "matches");
                        transaction2.commit();
                        break;
                    case R.id.nav_settings:
                        SettingsFragment frag3 = new SettingsFragment();
                        FragmentTransaction transaction3 = manager.beginTransaction();
                        transaction3.replace(R.id.container, frag3, "settings");
                        transaction3.commit();
                        break;
                }
                return true;
            }
        });

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shr_toolbar_menu, menu);
        return true;
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