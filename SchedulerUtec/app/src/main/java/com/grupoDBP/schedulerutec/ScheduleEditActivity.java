package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        // --------------------------- LOAD COURSES -------------------------
        JSONObject coursesData;
        try {
            coursesData = RequestHandeler.readCourseListByScheduleIdRequest(scheduleId);
            loadCourses(coursesData);
        } catch (JSONException e) {
            Log.e(this.getClass().getName(), "Invalid API Response (No courses found)");
            e.printStackTrace();
        }
    }
    
    
    public void onClickEditClasToSchedule(View view) {
        Button button_view = (Button) view;
        String clas_id = button_view.getTag(R.id.TAG_CLAS_ID).toString();
        String clas_subscribed = button_view.getTag(R.id.TAG_CLAS_SUBSCRIBED).toString();
        if (Boolean.parseBoolean(clas_subscribed)) {

            try{
                JSONObject jsonResponse = RequestHandeler.deleteClasFromScheduleByIdRequest(scheduleId,clas_id);
                boolean success = jsonResponse.getBoolean("success");
                if (success){
                    // Load new table
                    JSONArray new_schedule_matrix = jsonResponse.getJSONArray("table_horario");
                    TableLayout tb = findViewById(R.id.schedule_view_table_layout);
                    ScheduleTableUtils.loadScheduleTable(this, new_schedule_matrix, tb);
                    // Response to user
                    button_view.setTag(R.id.TAG_CLAS_SUBSCRIBED, false);
                    button_view.setText(R.string.generic_add_txt);
                    Log.v(this.getClass().getName(), "Class deleted from schedule");
                }
                else{
                    Log.e(this.getClass().getName(), "Could not delete class from schedule");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Could not parse API response");
            }


        }else{

            try {
                JSONObject jsonResponse = RequestHandeler.addClasToScheduleByIdRequest(scheduleId,clas_id);
                boolean success = jsonResponse.getBoolean("success");
                if (success){
                    // Load new table
                    JSONArray new_schedule_matrix = jsonResponse.getJSONArray("table_horario");
                    TableLayout tb = findViewById(R.id.schedule_view_table_layout);
                    ScheduleTableUtils.loadScheduleTable(this, new_schedule_matrix, tb);
                    // Response to user
                    button_view.setTag(R.id.TAG_CLAS_SUBSCRIBED, true);
                    button_view.setText(R.string.generic_delete_txt);
                    Log.v(this.getClass().getName(), "Class added to schedule");
                }
                else{
                    Log.e(this.getClass().getName(), "Could not add class to schedule");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Could not parse API response");
            }



        }
    }

    private void loadClasesList(JSONArray clas_list, String section, LinearLayout lin_layout) throws JSONException{
        for (int k = 0; k<clas_list.length(); k++){
            JSONObject clas = clas_list.getJSONObject(k);
            String clas_id = clas.getString("id");
            String clas_number = clas.getString("numero");
            String clas_profesor_first_name = clas.getString("docente_nombre");
            String clas_profesor_last_name = clas.getString("docente_apellido");
            Boolean clas_subscribed = clas.getBoolean("inscrito");

            TextView clasView = new TextView(this);
            clasView.setText(getString(R.string.schedule_edit_clas_txt, section, clas_number, clas_profesor_first_name, clas_profesor_last_name));
            Button clasEdit = new Button(this);
            clasEdit.setTag(R.id.TAG_CLAS_ID, clas_id) ;
            clasEdit.setTag(R.id.TAG_CLAS_SUBSCRIBED, clas_subscribed) ;
            if (clas_subscribed){
                clasEdit.setText(getString(R.string.generic_delete_txt));                
            }
            else {
                clasEdit.setText(getString(R.string.generic_add_txt));
            }
            clasEdit.setOnClickListener(this::onClickEditClasToSchedule);
            lin_layout.addView(clasView);
            lin_layout.addView(clasEdit);
        }
    }

    private void loadCourses(JSONObject coursesData) throws JSONException {
        JSONArray course_list= coursesData.getJSONArray("cursos");
        LinearLayout linear_layout = findViewById(R.id.schedule_edit_courses_layout);
        for (int i = 0; i<course_list.length(); i++) {
            JSONObject course = course_list.getJSONObject(i);
            String course_code =  course.getString("codigo");

            TextView course_view = new TextView(this);
            course_view.setText(course_code);
            linear_layout.addView(course_view);

            JSONArray section_list = course.getJSONArray("secciones");
            for (int j = 0; j<section_list.length(); j++){
                JSONObject section = section_list.getJSONObject(j);
                String section_number = section.getString("seccion");

                TextView section_view = new TextView(this);
                section_view.setText(section_number);
                linear_layout.addView(section_view);

                JSONArray labs_list = section.getJSONArray("labs");
                loadClasesList(labs_list, section_number, linear_layout);
                JSONArray theory_list = section.getJSONArray("labs");
                loadClasesList(theory_list, section_number, linear_layout);
                JSONArray virtual_theory_list = section.getJSONArray("labs");
                loadClasesList(virtual_theory_list, section_number, linear_layout);
            }
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