package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileViewActivity extends AppCompatActivity {

    // CLASS VARIABLES
    // JSON Objects
    JSONObject jsonData;
    // Is owner
    boolean isOwner;

    // FUNCTION: Schedule Link
    public void onClickScheduleButton(View view){
        Log.v(this.getClass().getName(), "Changing to Schedule Activity");
        Intent i = new Intent(this, ScheduleViewActivity.class);
        String schedule_id = view.getTag(R.id.TAG_SCHEDULE_ID).toString();
        i.putExtra(ScheduleViewActivity.EXTRA_SCHEDULE_ID, schedule_id);
        Log.v(this.getClass().getName(), "Starting Intent with Schedule ID: "+schedule_id);
        startActivity(i);
    }

    // FUNCTION: CREATE VIEW FOR Schedule
    public Button CreateScheduleView(String schedule_title, String schedule_id) {
        Button scheduleView = new Button(this);
        scheduleView.setText(getString(R.string.generic_schedule_text_view, schedule_title, schedule_id));
        scheduleView.setTag(R.id.TAG_SCHEDULE_ID, schedule_id);
        scheduleView.setOnClickListener(this::onClickScheduleButton);
        return scheduleView;
    }

    // FUNCTION: LOAD SCHEDULES INTO LinearLayout FROM JSONArray
    public void LoadScheduleList(JSONArray schedule_array, LinearLayout scheduleList, String alternative_text) throws JSONException {
        // If there are schedules process them
        if (schedule_array.length() > 0) {
            Log.v(this.getClass().getName(), "Schedules found. Now loading data ...");

            for (int i = 0; i <schedule_array.length(); i++){
                // Get schedule data
                JSONObject s = schedule_array.getJSONObject(i);
                String schedule_id = s.getString("horario_id");
                //String schedule_student_first_name = s.getString("horario_alumno_nombre");
                //String schedule_student_last_name = s.getString("horario_alumno_apellido");
                String schedule_title = s.getString("horario_titulo");
                //String schedule_url = s.getString("horario_url");

                // Create schedule view
                TextView scheduleView = CreateScheduleView(schedule_title, schedule_id);
                scheduleList.addView(scheduleView);
            }
        }
        // Else display empty text
        else{
            Log.v(this.getClass().getName(), "No schedules found. Displaying alternative text.");
            TextView noScheduleTextView = new TextView(this);
            noScheduleTextView.setText(alternative_text);
            scheduleList.addView(noScheduleTextView);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        // ------------------ API RESPONSE ---------------
        // Sample API JSON response
        String APIresponse = "{\"alumno_nombre\": \"Rodrigo Gabriel\", \"alumno_apellido\":\"Salazar Alva\",\"horarios\": [{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":1,\"horario_titulo\":\"Verano 2020-Opcion1\",\"horario_url\":\"/horarios/1\"}], \"favoritos\":[]}";

        // Process API Response
        try {
            // Process response into JSON object
            jsonData = new JSONObject(APIresponse);
        }
        catch (JSONException e) {
            // Error Handeling
            Log.e(this.getClass().getName(), "Invalid API Response (Can not parse to JSON)");
            e.printStackTrace(); return;
        }

        // Create new objects from loaded data
        // ------------------- PROFILE -----------------
        try {
            // Get profile TextView
            TextView profileTextView = findViewById(R.id.profile_main_title);
            // Get student data
            String student_first_name = jsonData.getString("alumno_nombre");
            String student_last_name = jsonData.getString("alumno_apellido");
            // Load data into view
            profileTextView.setText(getString(R.string.profile_view_profile_text_view, student_first_name, student_last_name));
        } catch (JSONException e) {
            // Error Handeling
            Log.e(this.getClass().getName(), "Invalid API Response (No student data found)");
            e.printStackTrace();
        }
        // ------------------- LOAD SCHEDULES -----------------
        try {
            // Get layout
            LinearLayout scheduleList = findViewById(R.id.profile_schedule_list);
            // Load array
            JSONArray schedule_array = jsonData.getJSONArray("horarios");
            // Load data to layout
            Log.v(this.getClass().getName(), "Starting Load of Student's schedules.");
            LoadScheduleList(schedule_array, scheduleList, getString(R.string.profile_view_schedule_text_view_empty));

        } catch (JSONException e) {
            // Error Handeling
            Log.e(this.getClass().getName(), "Invalid API Response (No schedule array found)");
            e.printStackTrace();
        }

        // ------------------- LOAD FAVORITES -----------------
        try {
            // Get layout
            LinearLayout favoriteList = findViewById(R.id.profile_favorites_list);
            // Load array
            JSONArray favorite_array = jsonData.getJSONArray("favoritos");
            // Load data to layout
            Log.v(this.getClass().getName(), "Starting Load of Student's favorites.");
            LoadScheduleList(favorite_array, favoriteList, getString(R.string.profile_view_favorite_text_view_empty));
        } catch (JSONException e) {
            // Error Handeling
            Log.e(this.getClass().getName(), "Invalid API Response (No favorites array found)");
            e.printStackTrace();
        }

        // Owner options:
        LinearLayout extraLayout = findViewById(R.id.profile_extra_layout);
        if (isOwner){
            extraLayout.setVisibility(View.GONE);
        }
        else {
            extraLayout.setVisibility(View.VISIBLE);
        }
    }


}