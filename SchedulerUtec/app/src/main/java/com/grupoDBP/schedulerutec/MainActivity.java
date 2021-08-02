package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void updateButtons() {
        View sectionProfile = findViewById(R.id.main_menu_profile_section);
        View sectionLogin = findViewById(R.id.main_menu_login_section);
        Log.v(this.getClass().getName(),"User is logged in?"+SessionData.loggedIn);
        if (SessionData.loggedIn){
            Log.v(this.getClass().getName(), "Loading menu for logged in user");
            sectionProfile.setVisibility(View.VISIBLE);
            sectionLogin.setVisibility(View.GONE);
        }
        else {
            Log.v(this.getClass().getName(), "Loading menu for unauthenticated user");
            sectionProfile.setVisibility(View.GONE);
            sectionLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateButtons();
    }

    public void onClickProfile(View view){
        Log.v(this.getClass().getName(), "Changing to Profile Activity");
        Intent i = new Intent(this, ProfileViewActivity.class);
        startActivity(i);
    }

    public void onClickLogin(View view){
        Log.v(this.getClass().getName(), "Changing to Login Activity");
        Intent i = new Intent(this, LoginActivity.class);
        startActivityForResult(i,0);
    }

    public void onClickExplore(View view) {
        Log.v(this.getClass().getName(), "Changing to Schedule Explore Activity");
        Intent i = new Intent(this, ScheduleExploreActivity.class);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateButtons();
    }
}