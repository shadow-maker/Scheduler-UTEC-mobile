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
        String APIresponse = "{\"horario_alumno_id\":\"202010387\",\"favorito\":\"false\",\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":1,\"horario_titulo\":\"Verano 2020-Opcion1\",\"horario_tabla\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"EG0006\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"EG0006\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"EG0006\",\"\",\"\",\"\"],[\"\",\"\",\"EG0006\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"EG0006\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"CS2B01\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"CS2701\",\"\",\"CS2701\",\"\",\"CS2701\",\"\",\"\"],[\"CS2701\",\"\",\"CS2701\",\"\",\"CS2701\",\"\",\"\"]]}";
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

    public static boolean updateDeleteFavoriteByIdResquest(String schedule_id) {
        return true;
    }

    public static boolean updateAddFavoriteByIdResquest(String schedule_id) {
        return true;
    }

    public static boolean deleteScheduleByIdRequest(String schedule_id) {
        return true;
    }

    public static JSONObject readCourseListByScheduleIdRequest(String schedule_id) throws JSONException {
        // Sample API JSON response
        String APIresponse ="{\"cursos\":[{\"codigo\":\"CSB01\",\"secciones\":[{\"seccion\":\"01\",\"labs\":[{\"id\":\"1\",\"numero\":\"01\",\"docente_nombre\":\"Marvin\",\"docente_apellido\":\"Abisrror Zarate\",\"inscrito\":\"true\"}],\"teorias\":[{\"id\":\"2\",\"numero\":\"00\",\"docente_nombre\":\"Marvin\",\"docente_apellido\":\"Abisrror Zarate\",\"inscrito\":\"true\"}],\"teorias_virtuales\":[]},{\"seccion\":\"02\",\"labs\":[{\"id\":\"3\",\"numero\":\"01\",\"docente_nombre\":\"Marvin\",\"docente_apellido\":\"Abisrror Zarate\",\"inscrito\":\"false\"}],\"teorias\":[{\"id\":\"4\",\"numero\":\"00\",\"docente_nombre\":\"Marvin\",\"docente_apellido\":\"Abisrror Zarate\",\"inscrito\":\"false\"}],\"teorias_virtuales\":[]}]}]}";
        return new JSONObject(APIresponse);
    }

    public static JSONObject addClasToScheduleByIdRequest(String schedule_id, String clas_id) throws JSONException {
        // Sample API JSON repsonse
        String APIresponse = "{\"pending_cursos\":\"CS2B01\",\"status_horario\":\"Pending\",\"success\":true,\"table_horario\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"]]}";
        return new JSONObject(APIresponse);
    }

    public static JSONObject deleteClasFromScheduleByIdRequest(String schedule_id, String clas_id) throws JSONException {
        // Sample API JSON repsonse
        String APIresponse = "{\"pending_cursos\":\"CS2B01\",\"status_horario\":\"Pending\",\"success\":true,\"table_horario\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"]]}";
        return new JSONObject(APIresponse);
    }
}
