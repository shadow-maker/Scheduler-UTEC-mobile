package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleViewActivity extends AppCompatActivity {
    // CONSTANTS
    public static final String EXTRA_SCHEDULE_ID = "EXTRA_SCHEDULE_ID";

    // VARIABLES
    // JSON Objects
    JSONObject jsonData;
    // Is owner
    boolean isOwner;
    // Schedule ID
    String scheduleId;
    // Is favorite
    boolean isFavorite;

    private void updateLoggedInButons() {
        Log.v(this.getClass().getName(), "Validating session");

        // Get elements
        View favoritesAdd = findViewById(R.id.schedule_view_add_favorite_layout);
        View favoritesDelete = findViewById(R.id.schedule_view_delete_favorite_layout);
        View extraLayout = findViewById(R.id.schedule_view_extra_layout);

        if (SessionData.loggedIn){
            Log.v(this.getClass().getName(), "User is logged in");

            if (isFavorite){
                favoritesDelete.setVisibility(View.VISIBLE);
                favoritesAdd.setVisibility(View.GONE);
                Log.v(this.getClass().getName(), "Schedule is a user favorite. Removing add favorite and keeping remove favorite.");
            }
            else {
                favoritesDelete.setVisibility(View.GONE);
                favoritesAdd.setVisibility(View.VISIBLE);
                Log.v(this.getClass().getName(), "Schedule is not a user favorite. Removing remove favorite favorite and keeping add favorite.");
            }

            if (isOwner){
                Log.v(this.getClass().getName(), "User is owner of schedule");
                extraLayout.setVisibility(View.VISIBLE);
            }
            else {
                Log.v(this.getClass().getName(), "User is not owner of schedule. Removing owner options layout.");
                extraLayout.setVisibility(View.GONE);
            }
        }
        else{
            Log.v(this.getClass().getName(), "User is not logged in. Removing favorites and owner options layouts.");
            favoritesAdd.setVisibility(View.GONE);
            favoritesDelete.setVisibility(View.GONE);
            extraLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);

        // ----------------- INTENT PROCESSING -----------------
        Intent i = getIntent();
        scheduleId = i.getStringExtra(EXTRA_SCHEDULE_ID);
        Log.v(this.getClass().getName(), "Schedule ID extracted from ID: " + scheduleId);

        // ------------------ API RESPONSE ---------------
        // Process API Response
        try {
            // Process response into JSON object
            jsonData = RequestHandeler.readScheduleByIdRequest(scheduleId);
        }
        catch (JSONException e) {
            // Error Handeling
            Log.e(this.getClass().getName(), "Invalid API Response (Can not parse to JSON)");
            e.printStackTrace(); return;
        }


        // ---------------------------- LOAD TABLE ---------------------------
        TableLayout tb = findViewById(R.id.schedule_view_table_layout);
        try {
            JSONArray schedule_matrix = jsonData.getJSONArray("horario_tabla");
            isOwner = jsonData.getString("horario_alumno_id").equals(SessionData.userId);

            try {
                isFavorite = jsonData.getBoolean("favorite");
            } catch (JSONException e) {
                Log.w(this.getClass().getName(), "Favorite JSON element not d. Setting false by default");
                isFavorite = false;
            }


            ScheduleTableUtils.loadScheduleTable(this, schedule_matrix, tb);
        } catch (JSONException e) {
            Log.e(this.getClass().getName(), "Invalid API Response (No schedule matrix found)");
            e.printStackTrace();
        }

        // --------------------------- NAME AND CREATOR -------------------
        try {
            // Get views
            TextView studentTextView = findViewById(R.id.schedule_view_student);
            TextView titleTextView = findViewById(R.id.schedule_view_name);
            // Get student data
            String schedule_name= jsonData.getString("horario_titulo");
            String schedule_student_first_name = jsonData.getString("horario_alumno_nombre");
            String schedule_student_last_name = jsonData.getString("horario_alumno_apellido");
            // Set text
            studentTextView.setText(getString(R.string.schedule_view_student_txt, schedule_student_first_name, schedule_student_last_name));
            titleTextView.setText(getString(R.string.schedule_view_name_txt, schedule_name));
        } catch (JSONException e) {
            // Error Handeling
            Log.e(this.getClass().getName(), "Invalid API Response (No student/title data found)");
            e.printStackTrace();
        }

        // ----------------------- LOGGED IN OPTIONS -------------------------
        updateLoggedInButons();
    }

    // FUNCTION: Schedule Link
    public void onClickScheduleEditButton(View view){
        Log.v(this.getClass().getName(), "Changing to Schedule Edit Activity");
        Intent i = new Intent(this, ScheduleEditActivity.class);
        i.putExtra(ScheduleEditActivity.EXTRA_SCHEDULE_EDIT_ID, scheduleId);
        Log.v(this.getClass().getName(), "Starting Intent with Schedule ID: "+scheduleId);
        startActivity(i);
    }


    // Function on click delete favorite
    public void onClickScheduleFavoriteDelete(View view){
        if (RequestHandeler.updateDeleteFavoriteByIdResquest(scheduleId)) {
            isFavorite = false;
            Toast.makeText(this, R.string.schedule_view_delete_favorite_txt, Toast.LENGTH_LONG).show();
            Log.e(this.getClass().getName(), "Added schedule with ID: "+scheduleId+ " to favorites");
        }
        updateLoggedInButons();
    }

    // Function on click delete favorite
    public void onClickScheduleFavoriteAdd(View view){
        if (RequestHandeler.updateAddFavoriteByIdResquest(scheduleId)) {
            isFavorite = true;
            Toast.makeText(this, R.string.schedule_view_add_favorite_txt, Toast.LENGTH_SHORT).show();
            Log.e(this.getClass().getName(), "Deleted schedule with ID: "+scheduleId+ " from favorites");
        }
        updateLoggedInButons();
    }

    // Function: On click delete schedule
    public void onClickScheduleDelete(View view){
        if (RequestHandeler.deleteScheduleByIdRequest(scheduleId)){
            Toast.makeText(this, R.string.schedule_view_delete_schedule_txt, Toast.LENGTH_SHORT).show();
            Log.e(this.getClass().getName(), "Deleted schedule with ID: "+scheduleId);
            finish();
        }
        else {
            Toast.makeText(this, R.string.generic_invalid_response, Toast.LENGTH_LONG).show();
            Log.e(this.getClass().getName(), "Could not delete schedule with ID: "+scheduleId);
        }
    }
}