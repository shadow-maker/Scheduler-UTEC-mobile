package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ScheduleViewActivity extends AppCompatActivity {
    // CONSTANTS
    public static final String EXTRA_SCHEDULE_ID = "EXTRA_SCHEDULE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);

        Intent i = getIntent();
        String schedule_id = i.getStringExtra(EXTRA_SCHEDULE_ID);
        Log.v(this.getClass().getName(), "Schedule ID extracted from ID: " + schedule_id);

    }
}