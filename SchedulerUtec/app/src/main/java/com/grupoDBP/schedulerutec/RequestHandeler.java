package com.grupoDBP.schedulerutec;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestHandeler {

    public static boolean loginRequest(String id_input, String password_input) throws JSONException {
        // Sample APIresponse
        String APIresponse;
        if (id_input.equals("202010387") & password_input.equals("sample")) {
            APIresponse = "{\"success\":\"true\",\"id\":\"202010387\",\"nombre\":\"Rodrigo Gabriel\",\"apellido\":\"Salazar Alva\"}";
        } else{
            APIresponse =  "{\"success\":\"false\"}";
        }
        JSONObject jsonData = new JSONObject(APIresponse);

        // Process response
        if (jsonData.getBoolean("success")){
            Log.v("RequestHandeler", "Loggin succesful");
            SessionData.loggedIn=true;
            SessionData.userId=jsonData.getString("id");
            return true;
        }
        else {
            Log.v("RequestHandeler","Failed login");
            return false;
        }
    }

    public static JSONObject readProfileByIdRequest(String student_id) throws JSONException {
        // Sample API JSON response
        String APIresponse = "{\"alumno_id\":1,\"alumno_nombre\": \"Rodrigo Gabriel\", \"alumno_apellido\":\"Salazar Alva\",\"horarios\": [{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":1,\"horario_titulo\":\"Verano 2020-Opcion1\",\"horario_url\":\"/horarios/1\"}], \"favoritos\":[]}";

        // Process API Response
       return new JSONObject(APIresponse);
    }

    public static JSONObject readScheduleByIdRequest(String schedule_id) throws JSONException {
        String APIresponse = "{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":1,\"horario_titulo\":\"Verano 2020-Opcion1\",\"horario_tabla\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"EG0006\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"EG0006\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"EG0006\",\"\",\"\",\"\"],[\"\",\"\",\"EG0006\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"EG0006\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"CS2B01\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"CS2701\",\"\",\"CS2701\",\"\",\"CS2701\",\"\",\"\"],[\"CS2701\",\"\",\"CS2701\",\"\",\"CS2701\",\"\",\"\"]]}";
        // Process response into JSON object
        return new JSONObject(APIresponse);
    }

    public static JSONObject createScheduleRequest(String schedule_title) throws JSONException {
        // Create JSON
        JSONObject submitJSON = new JSONObject();
        try {
            submitJSON.put("horario_titulo", schedule_title);
        } catch (JSONException e) {
            Log.e("Request Handeler","Unexpected error creating JSON for new schedule");
            e.printStackTrace();
            return new JSONObject();
        }
        // Sample API JSON response
        String APIresponse = "{\"success\":\"true\",\"id\":\"10\"}";
        // Process response into JSON object
        return new JSONObject(APIresponse);
    }

    public static boolean updateScheduleTitleRequest(String schedule_title) throws JSONException {
        // Sample API JSON response
        String APIresponse = "{\"success\":\"true\"}";

        JSONObject jsonData = new JSONObject(APIresponse);
        // Process response
        if (jsonData.getBoolean("success")){
            Log.v("RequestHandeler", "Title update succesful");
            return true;
        }
        else {
            Log.v("RequestHandeler","Title update failed");
            return false;
        }
    }
}
