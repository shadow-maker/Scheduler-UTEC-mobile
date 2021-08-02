package com.grupoDBP.schedulerutec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class ScheduleExploreActivity extends AppCompatActivity {

    List<ScheduleElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_explore);

        init();
    }

    public void init() {
        elements = new ArrayList<>();
        elements.add(new ScheduleElement(1, "Horario 1", "Persona 1", "2021-08-01"));
        elements.add(new ScheduleElement(2, "Horario 2", "Persona 2", "2021-08-02"));
        elements.add(new ScheduleElement(3, "Horario 3", "Persona 3", "2021-08-03"));

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