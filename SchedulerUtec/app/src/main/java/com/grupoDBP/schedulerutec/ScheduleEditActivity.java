package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ScheduleEditActivity extends AppCompatActivity {
    // CONSTANTS
    public static final String EXTRA_SCHEDULE_EDIT_ID = "EXTRA_SCHEDULE_EDIT_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);
    }

    public void onClickSchedule(View view){
        Log.v(this.getClass().getName(), "Changing to Schedule View Activity");
        Intent i = new Intent(this, ScheduleViewActivity.class);
        startActivity(i);
    }
}