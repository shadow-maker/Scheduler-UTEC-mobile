package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleViewActivity extends AppCompatActivity {
    // CONSTANTS
    public static final String EXTRA_SCHEDULE_ID = "EXTRA_SCHEDULE_ID";

    // CLASS VARIABLES
    // JSON Objects
    JSONObject jsonData;
    // Is owner
    boolean isOwner;
    // Schedule ID
    String scheduleId;

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
    }

    // FUNCTION: Schedule Link
    public void onClickScheduleEditButton(View view){
        Log.v(this.getClass().getName(), "Changing to Schedule Edit Activity");
        Intent i = new Intent(this, ScheduleViewActivity.class);
        i.putExtra(ScheduleEditActivity.EXTRA_SCHEDULE_EDIT_ID, scheduleId);
        Log.v(this.getClass().getName(), "Starting Intent with Schedule ID: "+scheduleId);
        startActivityForResult(i,0);
    }

}