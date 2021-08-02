package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ScheduleEditActivity extends AppCompatActivity {
    // CONSTANTS
    public static final String EXTRA_SCHEDULE_EDIT_ID = "EXTRA_SCHEDULE_EDIT_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);
    }
}