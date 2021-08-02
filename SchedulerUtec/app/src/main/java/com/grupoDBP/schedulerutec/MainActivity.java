package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickProfile(View view){
        Log.v(this.getClass().getName(), "Changing to Profile Activity");
        Intent i = new Intent(this, ProfileViewActivity.class);
        startActivity(i);
    }

    public void onClickLogin(View view){
        Log.v(this.getClass().getName(), "Changing to Login Activity");
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void onClickExplore(View view){
        Log.v(this.getClass().getName(), "Changing to Login Activity");
        Intent i = new Intent(this, ScheduleExploreActivity.class);
        startActivity(i);
    }

}