package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThanksActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        mTextView = (TextView) findViewById(R.id.text);

        StringBuilder msg = new StringBuilder(("Thanks for signing up "));
        Intent intent = getIntent();
        Bundle b = intent.getExtras();


        String usrName = "Blank";

        if(b != null) {
            if(b.containsKey((Constants.KEY_NAME))) {
                usrName = b.getString(Constants.KEY_NAME);
            }
        }

        msg.append(usrName).append("!");
        mTextView.setText(msg);
    }


    public void goToForm(View view) {
        finish();
    }


}