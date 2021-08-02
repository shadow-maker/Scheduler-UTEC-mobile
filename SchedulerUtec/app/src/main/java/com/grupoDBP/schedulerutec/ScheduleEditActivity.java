package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;

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
}