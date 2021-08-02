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

    private ArrayList<ArrayList<View>> getMatrixFromTable(View v){
        ViewGroup vg = (ViewGroup) v;
        Log.v(this.getClass().getName(), "#N row in table " + vg.getChildCount()+ ". Now loading data ...");

        ArrayList<ArrayList<View>> table_matrix = new ArrayList<ArrayList<View>>();
        for (int i = 0; i <vg.getChildCount(); i++){
            ViewGroup row = (ViewGroup) vg.getChildAt(i);
            ArrayList<View> temp_array = new ArrayList<View>();
            for (int j = 0; j<row.getChildCount(); j++){
                View cell = row.getChildAt(j);
                temp_array.add(cell);
            }
            table_matrix.add(temp_array);
        }
        Log.v(this.getClass().getName(), "Succesfully loaded matrix from table");
        return table_matrix;
    }

    private void loadScheduleTable(JSONArray schedule_matrix, TableLayout tb) throws JSONException {
        ArrayList<ArrayList<View>> table_matrix = getMatrixFromTable(tb);

        for (int i = 0; i < schedule_matrix.length(); i++){
            JSONArray schedule_row = schedule_matrix.getJSONArray(i);
            for (int j = 0; j<schedule_row.length(); j++){
                String cell_text = schedule_row.getString(j);
                //String cell_text = c.toString();
                TextView cell = (TextView) table_matrix.get(i+1).get(j+1);
                cell.setText(cell_text);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);

        // ------------ INTENT PROCESSING
        Intent i = getIntent();
        scheduleId = i.getStringExtra(EXTRA_SCHEDULE_ID);
        Log.v(this.getClass().getName(), "Schedule ID extracted from ID: " + scheduleId);

        // ------------------ API RESPONSE ---------------
        // Sample API JSON response
        String APIresponse = "{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":1,\"horario_titulo\":\"Verano 2020-Opcion1\",\"horario_tabla\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"EG0006\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"EG0006\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"EG0006\",\"\",\"\",\"\"],[\"\",\"\",\"EG0006\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"EG0006\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"CS2B01\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"CS2701\",\"\",\"CS2701\",\"\",\"CS2701\",\"\",\"\"],[\"CS2701\",\"\",\"CS2701\",\"\",\"CS2701\",\"\",\"\"]]}";
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


        // ----------------- LOAD TABLE ----------------------
        TableLayout tb = findViewById(R.id.schedule_view_table_layout);
        try {
            JSONArray schedule_matrix = jsonData.getJSONArray("horario_tabla");
            loadScheduleTable(schedule_matrix, tb);
        } catch (JSONException e) {
            Log.e(this.getClass().getName(), "Invalid API Response (No schedule matrix found)");
            e.printStackTrace();
        }

        // ----------------- NAME AND CREATOR -------------------
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
}