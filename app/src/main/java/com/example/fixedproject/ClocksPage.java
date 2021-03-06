package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.DigitalClock;

import java.io.InvalidClassException;

public class ClocksPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocks_page);

        AnalogClock ac = (AnalogClock) findViewById(R.id.analog);
        DigitalClock dc = (DigitalClock) findViewById(R.id.digital);
    }
    public void onClick(View view) {
        Intent i;
        if(view.getId()==R.id.ToUser) i = new Intent(this, UserInfo.class);
        else if (view.getId()==R.id.ToMeetingsList)
            i = new Intent(this, DataSelector.class);
        else if (view.getId()==R.id.ToStopWatch)
            i = new Intent(this, StopWatch.class);
        else if (view.getId()==R.id.ToTimer)
            i = new Intent(this, Timer.class);
        else if (view.getId()==R.id.ClocksToDisplay)
            i = new Intent(this, MeetingsList.class);
        else
            i = new Intent(this, MainActivity.class);
        i.putExtra("_id",getIntent().getExtras().getInt("_id"));
        startActivity(i);
    }
    public void logOff(View view){
        Intent i;
        if(view.getId()==R.id.log_off)
            i = new Intent(this, MainActivity.class);
        else
            i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}