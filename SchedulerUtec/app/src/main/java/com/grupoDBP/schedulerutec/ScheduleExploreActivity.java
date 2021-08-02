package com.grupoDBP.schedulerutec;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class ScheduleExploreActivity extends AppCompatActivity {

    List<ListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_explore);
    }

    public void init() {
        elements = new ArrayList<>();
        elements.add(new ListElement("Horario 1", "Persona 1", "2021-08-01"));
        elements.add(new ListElement("Horario 2", "Persona 2", "2021-08-02"));
        elements.add(new ListElement("Horario 3", "Persona 3", "2021-08-03"));
    }

    ListAdapter listAdapter = new ListAdapter(elements, this);
    RecyclerView recyclerView = findViewById(R.id.scheduleList);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new, LinearLayoutManager(this));
    recyclerView.setAdapter(listAdapter);
}