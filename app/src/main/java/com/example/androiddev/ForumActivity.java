package com.example.androiddev;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ForumActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView mTextView;
    private TextView tvDate;
    private TextView userName;
    private Button btPickDate;
    private EditText eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        mTextView = findViewById(R.id.text);

        tvDate = findViewById(R.id.tvDate);
        btPickDate = findViewById(R.id.btPickDate);
        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Please note that use your package name here
                com.example.androiddev.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.androiddev.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        EditText e1 = findViewById(R.id.names);
        EditText e2 = findViewById(R.id.email);
        EditText e3 = findViewById(R.id.username);

        e1.setText("");
        e2.setText("");
        e3.setText("");
        tvDate.setText("Date");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState.containsKey(Constants.KEY_TEXTVIEW_TEXT)) {
            mTextView.setText(savedInstanceState.getString(Constants.KEY_TEXTVIEW_TEXT));
        }

        if(savedInstanceState.containsKey(Constants.KEY_AGE)) {
            tvDate.setText(savedInstanceState.getString(Constants.KEY_AGE));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState,outPersistentState);

        outState.putString(Constants.KEY_TEXTVIEW_TEXT, mTextView.getText().toString());
        outState.putString(Constants.KEY_AGE,tvDate.getText().toString());

    }

    public void submitForm(View view) {
        mTextView = findViewById(R.id.names);
        userName = findViewById(R.id.username);
        eText = findViewById(R.id.email);
        if(!isValidEmail(eText.getText())){
            eText.setError("Email not valid!");
            return;
        }

        if(mTextView.getText().toString().matches("")){
            mTextView.setError("Cannot Be Blank!");
            return;
        }

        if(userName.getText().toString().matches("")){
            userName.setError("Cannot Be Blank!");
            return;
        }

        Intent intent = new Intent(ForumActivity.this, ThanksActivity.class);
        Bundle bundle = new Bundle();


        bundle.putString(Constants.KEY_NAME, userName.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Date eighteen = new Date(1050871680000L);
        Button b = findViewById(R.id.button2);
        tvDate = findViewById(R.id.tvDate);
        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalender.getTime());
        if(mCalender.getTime().compareTo(eighteen) > 0) {
            tvDate.setError("Must Be Older Than 18!");
            b.setEnabled(false);
        } else {
            tvDate.setText(selectedDate);
            b.setEnabled(true);
        }

    }
}