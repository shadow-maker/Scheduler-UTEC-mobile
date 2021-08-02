package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScheduleEditActivity extends AppCompatActivity {
    // CONSTANTS
    public static final String EXTRA_SCHEDULE_EDIT_ID = "EXTRA_SCHEDULE_EDIT_ID";

    // VARIABLES
    // JSON Objects
    JSONObject jsonData;
    // Is owner
    boolean isOwner;
    // Schedule ID
    String scheduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(this.getClass().getName(), "Loading Edit Schedule Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);

        // ---------------- INTENT PROCESSING -------------------
        Intent i = getIntent();
        scheduleId = i.getStringExtra(EXTRA_SCHEDULE_EDIT_ID);
        Log.v(this.getClass().getName(), "Schedule ID extracted from ID: " + scheduleId);

        // ----------------- API RESPONSE (schedule) -------------------
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

        // ---------------------------- LOAD TABLE ---------------------------
        TableLayout tb = findViewById(R.id.schedule_view_table_layout);
        try {
            JSONArray schedule_matrix = jsonData.getJSONArray("horario_tabla");
            ScheduleTableUtils.loadScheduleTable(this, schedule_matrix, tb);
        } catch (JSONException e) {
            Log.e(this.getClass().getName(), "Invalid API Response (No schedule matrix found)");
            e.printStackTrace();
        }
    }

    public void onClickReturn(View view) {
        finish();
    }

    public void onClickUpdateTitleSubmit(View view){
        // Retrieve data
        EditText titleInput = findViewById(R.id.rename_edit);
        String schedule_title = titleInput.getText().toString();

        // Get view to be updated
        TextView titleTextView = findViewById(R.id.schedule_view_name);

        // Submit Request through API
        boolean success;
        try {
            success = RequestHandeler.updateScheduleTitleRequest(schedule_title);
        } catch (JSONException e) {
            Toast.makeText(this, getString(R.string.generic_connection_error), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            Log.w(this.getClass().getName(),"Invalid API response when updating schedule'stitle");
            return;
        }
        if (success) {
            // Success
            titleTextView.setText(getString(R.string.schedule_view_name_txt, schedule_title));
            Toast.makeText(this, getString(R.string.schedule_edit_title_update_success_txt), Toast.LENGTH_SHORT).show();
            Log.v(this.getClass().getName(),"Schedule's title updated succesfully");
        }
        else {
            // Success
            Toast.makeText(this, getString(R.string.generic_invalid_response), Toast.LENGTH_SHORT).show();
            Log.w(this.getClass().getName(),"Could not update schedule's title");
        }

    }
}