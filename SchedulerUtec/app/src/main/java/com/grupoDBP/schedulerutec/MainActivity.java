package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Button boton_explore, boton_profile, boton_loggin;  // Esto inicia el back button
    public void updateButtons() {
        View sectionProfile = findViewById(R.id.main_menu_profile_section);
        View sectionLogin = findViewById(R.id.main_menu_login_section);
        View sectionLogout = findViewById(R.id.main_menu_logout_section);
        Log.v(this.getClass().getName(),"User is logged in?"+SessionData.loggedIn);
        if (SessionData.loggedIn){
            Log.v(this.getClass().getName(), "Loading menu for logged in user");
            sectionProfile.setVisibility(View.VISIBLE);
            sectionLogout.setVisibility(View.VISIBLE);
            sectionLogin.setVisibility(View.GONE);
        }
        else {
            Log.v(this.getClass().getName(), "Loading menu for unauthenticated user");
            sectionProfile.setVisibility(View.GONE);
            sectionLogout.setVisibility(View.GONE);
            sectionLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateButtons();
        /*
        boton_loggin = findViewById(R.id.boton_loggin);
        boton_loggin.setOnClickListener(new View.OnClickListener() { // ACA ESTA LA FUNCION PARA EL BACK BUTTON Loggin TO MAIN
            @Override
            public void onClick(View v) {
                Intent Loggin_to_Main = new Intent(MainActivity.this,LoginActivity.class );
                startActivity(Loggin_to_Main);
            }
        });
        */
        /*
        boton_profile = findViewById(R.id.boton_profile);
        boton_profile.setOnClickListener(new View.OnClickListener() { // ACA ESTA LA FUNCION PARA EL BACK BUTTON PROFILE TO MAIN
            @Override
            public void onClick(View v) {
                Intent Profile_view_to_Main = new Intent(MainActivity.this,ProfileViewActivity.class );
                startActivity(Profile_view_to_Main);
            }
        });

        boton_explore = findViewById(R.id.boton_explore);
        boton_explore.setOnClickListener(new View.OnClickListener(){    // ACA ESTA LA FUNCION PARA EL BACK BUTTON EXPLORE TO MAIN
                @Override
                public void onClick(View view){

                    Intent Explore_to_Main = new Intent(MainActivity.this,ScheduleExploreActivity.class );
                    startActivity(Explore_to_Main);

                }
        });
           */
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

    public void onClickLogout(View view) {
        Log.v(this.getClass().getName(), "Logginout");
        SessionData.loggedIn = false;
        SessionData.userId = null;
        Log.v(this.getClass().getName(), "Credentials deleted");
        updateButtons();
        Toast.makeText(this, getString(R.string.generic_successful_logout), Toast.LENGTH_SHORT).show();
        Log.v(this.getClass().getName(), "Buttons updated and sending toast");
    }
}