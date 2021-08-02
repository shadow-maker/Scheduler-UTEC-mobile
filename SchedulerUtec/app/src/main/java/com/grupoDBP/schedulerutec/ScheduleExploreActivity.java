package com.grupoDBP.schedulerutec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class ScheduleExploreActivity extends AppCompatActivity {

    List<ScheduleElement> elements;
    JSONObject jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_explore);

        init();
    }

    public void init() {
        elements = new ArrayList<>();

        // LOAD JSON DATA
        try {
            // Process response into JSON object
            jsonData = RequestHandeler.readScheduleAll();
        }
        catch (JSONException e) {
            // Error Handeling
            Log.e(this.getClass().getName(), "Invalid API Response (Can not parse to JSON)");
            e.printStackTrace(); return;
        }

        // LOAD SCHEDULES

        Log.v(this.getClass().getName(), "Loading list of schedules");
        try {
            // Load array
            JSONArray schedule_array = jsonData.getJSONArray("horarios");
            // Load data to layout
            Log.v(this.getClass().getName(), "Starting Load of schedules.");

            for (int i = 0; i < schedule_array.length(); i++){
                // Get schedule data
                JSONObject s = schedule_array.getJSONObject(i);
                String schedule_id = s.getString("horario_id");
                String schedule_title = s.getString("horario_titulo");
                String schedule_student_first_name = s.getString("horario_alumno_nombre");
                String schedule_student_last_name = s.getString("horario_alumno_apellido");
                String schedule_student_name = schedule_student_first_name + schedule_student_last_name;

                elements.add(new ScheduleElement(schedule_id, schedule_title,  schedule_student_name));

            }

        } catch (JSONException e) {
            // Error Handeling
            Log.e(this.getClass().getName(), "Invalid API Response (No schedule array found)");
            e.printStackTrace();
        }

        ScheduleListAdapter listAdapter = new ScheduleListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.scheduleList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    // ------------ BUTTON CLICK LISTENERS -----------------------
    public void onClickScheduleButty(View view){
        // TODO
    }
}