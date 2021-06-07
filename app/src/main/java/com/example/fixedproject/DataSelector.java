package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DataSelector extends AppCompatActivity {
    private static final String TAG = "SelectSchedule";
    Intent i = getIntent();
    Button sdateButton, stimeButton, edatebutton, etimebutton;
    TextView sdateTextView, stimeTextView, edateTextView, etimeTextView;
    Type type;
    Meetings meetings = new Meetings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_selector);

        sdateButton = findViewById(R.id.start_date_btn);
        stimeButton = findViewById(R.id.start_time_btn);
        sdateTextView = findViewById(R.id.start_date_TV);
        stimeTextView = findViewById(R.id.start_time_TV);
        edatebutton = findViewById(R.id.end_date_btn);
        etimebutton = findViewById(R.id.end_time_btn);
        edateTextView = findViewById(R.id.end_date_TV);
        etimeTextView = findViewById(R.id.end_time_TV);
        //this.meetings = meetings;


        sdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDatesButtons(view);
            }
        });

        edatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDatesButtons(view);
            }
        });

        stimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButtons(view);
            }
        });
        
        etimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { handleTimeButtons(view); }
        });
    }

    private void handleDatesButtons(View v){
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int YEAR = calendar.get(java.util.Calendar.YEAR);
        int MONTH = calendar.get(java.util.Calendar.MONTH);
        int DATE = calendar.get(java.util.Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                calendar1.set(java.util.Calendar.YEAR, year);
                calendar1.set(java.util.Calendar.MONTH, month);
                calendar1.set(java.util.Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                if(v.getId() == R.id.start_date_btn) { sdateTextView.setText(dateText);
                meetings.setSyear(year);
                meetings.setSmonth(month);
                meetings.setSday(date);
                } //dateTextView
                else if(v.getId() == R.id.end_date_btn) { edateTextView.setText(dateText);
                    meetings.setEyear(year);
                    meetings.setEmonth(month);
                    meetings.setEday(date);
                }
                //else throw
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void handleTimeButtons(View v) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int HOUR = calendar.get(java.util.Calendar.HOUR);
        int MINUTE = calendar.get(java.util.Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeSet: " + hour + minute);
                java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                calendar1.set(java.util.Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();

                if(v.getId()==R.id.start_time_btn) {stimeTextView.setText(dateText);
                meetings.setShour(hour);
                meetings.setSminute(minute);
                }
                else if(v.getId()==R.id.end_time_btn) {etimeTextView.setText(dateText);
                meetings.setEhour(hour);
                meetings.setEminute(minute);
                }
            }
        }, HOUR, MINUTE, is24HourFormat);
        timePickerDialog.show();
    }
    public void onClick(View view) {
        if(view.getId()==R.id.Todisplay)
            i = new Intent(this, MeetingsList.class);
        else if (view.getId()==R.id.ToClocksPage2)
            i = new Intent(this, ClocksPage.class);
        startActivity(i);
    }
    public void btnClick(View view)
    {
        final String[] types = {"leisure", "work", "private", "other"};
        ListAdapter aryListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,types);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("title");
        //failed
        builder.setAdapter(aryListAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int type) {
                        Toast.makeText(DataSelector.this, types[type],Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.getListView().setBackgroundColor(Color.GRAY);
        dialog.show();
    }
    public void Save(View view)
    {
       // if(meetings.getTitle().equals("") &&)

    }
}