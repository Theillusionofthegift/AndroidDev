package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ForumActivity extends AppCompatActivity {

    private TextView mTextView;
    private CalendarView calender;
    private TextView date_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        mTextView = findViewById(R.id.text);
        calender = (CalendarView)
                findViewById(R.id.calendarView3);
        date_view = (TextView)
                findViewById(R.id.date_view);


        calender.setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {
                                Button b = findViewById(R.id.button2);
                                if (year < 2003) {
                                    String Date
                                            = dayOfMonth + "-"
                                            + (month + 1) + "-" + year;

                                    // set this date in TextView for Display
                                    date_view.setText(Date);
                                    b.setEnabled(true);
                                } else {
                                    date_view.setText("Must Be 18!");
                                    b.setEnabled(false);
                                }
                            }
                        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState.containsKey(Constants.KEY_TEXTVIEW_TEXT)) {
            mTextView.setText(savedInstanceState.getString(Constants.KEY_TEXTVIEW_TEXT));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState,outPersistentState);

        outState.putString(Constants.KEY_TEXTVIEW_TEXT, mTextView.getText().toString());

    }


    public void submitForm(View view) {
        mTextView = findViewById(R.id.names);


        Intent intent = new Intent(ForumActivity.this, Thanks.class);
        Bundle bundle = new Bundle();

        TextView t = findViewById(R.id.username);
        bundle.putString(Constants.KEY_NAME, t.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }


}