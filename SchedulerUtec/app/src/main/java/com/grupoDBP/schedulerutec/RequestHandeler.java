package com.grupoDBP.schedulerutec;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestHandeler {

    public static JSONObject readProfileByIdRequest(String student_id) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getStudentReadURL(student_id);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"alumno_id\":1,\"alumno_nombre\": \"Rodrigo Gabriel\", \"alumno_apellido\":\"Salazar Alva\",\"horarios\": [{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":1,\"horario_titulo\":\"Verano 2020-Opcion1\",\"horario_url\":\"/horarios/1\"}], \"favoritos\":[]}";
        // -------------- PROCESS RESPONSE --------------
       return new JSONObject(APIresponse);
    }

    public static JSONObject readScheduleByIdRequest(String schedule_id) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getScheduleReadURL(schedule_id);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"horario_alumno_id\":\"202010387\",\"favorito\":\"false\",\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":1,\"horario_titulo\":\"Verano 2020-Opcion1\",\"horario_tabla\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"EG0006\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"EG0006\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"EG0006\",\"\",\"\",\"\"],[\"\",\"\",\"EG0006\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"EG0006\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"CS2B01\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"CS2701\",\"\",\"CS2701\",\"\",\"CS2701\",\"\",\"\"],[\"CS2701\",\"\",\"CS2701\",\"\",\"CS2701\",\"\",\"\"]]}";
        // -------------- PROCESS RESPONSE --------------
        return new JSONObject(APIresponse);
    }

    public static JSONObject readScheduleAll() throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getAllSheduleReadURL();
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"empty\":false,\"horarios\":[{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":1,\"horario_titulo\":\"Verano 2020-Opcion1\",\"horario_url\":\"/horarios/1\"},{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":2,\"horario_titulo\":\"Verano 2020-Opcion2\",\"horario_url\":\"/horarios/2\"},{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":3,\"horario_titulo\":\"Verano 2020-Opcion3\",\"horario_url\":\"/horarios/3\"},{\"horario_alumno_apellido\":\"Ortiz Martinez\",\"horario_alumno_nombre\":\"C\\u00e9sar Alejandro\",\"horario_id\":4,\"horario_titulo\":\"SIoSI\",\"horario_url\":\"/horarios/4\"},{\"horario_alumno_apellido\":\"Yupanqui Mera\",\"horario_alumno_nombre\":\"Leonardo Fabricio\",\"horario_id\":5,\"horario_titulo\":\"MiOpcion\",\"horario_url\":\"/horarios/5\"},{\"horario_alumno_apellido\":\"Perez\",\"horario_alumno_nombre\":\"Pedro\",\"horario_id\":6,\"horario_titulo\":\"Verano2 2020\",\"horario_url\":\"/horarios/6\"},{\"horario_alumno_apellido\":\"test\",\"horario_alumno_nombre\":\"tes\",\"horario_id\":9,\"horario_titulo\":\"test\",\"horario_url\":\"/horarios/9\"},{\"horario_alumno_apellido\":\"Salazar Alva\",\"horario_alumno_nombre\":\"Rodrigo Gabriel\",\"horario_id\":10,\"horario_titulo\":\"test\",\"horario_url\":\"/horarios/10\"}],\"success\":true}";
        // -------------- PROCESS RESPONSE --------------
        return new JSONObject(APIresponse);
    }

    public static JSONObject createScheduleRequest(String schedule_title) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getScheduleCreateURL();
        // ---------------- SUBMIT JSON -----------------
        JSONObject submitJSON = new JSONObject();
        submitJSON.put("horario_titulo", schedule_title);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"success\":\"true\",\"id\":\"10\"}";
        // -------------- PROCESS RESPONSE --------------
        return new JSONObject(APIresponse);
    }

    public static boolean updateScheduleTitleRequest(String schedule_id, String schedule_title) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getScheduleUpdateRenameURL(schedule_id);
        // ---------------- SUBMIT JSON -----------------
        JSONObject submitJSON = new JSONObject();
        submitJSON.put("horario_titulo", schedule_title);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"success\":\"true\"}";
        // -------------- PROCESS RESPONSE --------------
        JSONObject jsonData = new JSONObject(APIresponse);
        if (jsonData.getBoolean("success")){
            Log.v("RequestHandeler", "Title update succesful");
            return true;
        }
        else {
            Log.v("RequestHandeler","Title update failed");
            return false;
        }
    }

    public static boolean updateDeleteFavoriteByIdResquest(String schedule_id) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getFavoriteDeleteURL(schedule_id);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"success\":\"true\"}";
        // -------------- PROCESS RESPONSE --------------
        JSONObject jsonData = new JSONObject(APIresponse);
        if (jsonData.getBoolean("success")){
            Log.v("RequestHandeler", "Delete from favorite succesful");
            return true;
        }
        else {
            Log.v("RequestHandeler","Delete from favorite failed");
            return false;
        }
    }

    public static boolean updateAddFavoriteByIdResquest(String schedule_id) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getFavoriteAddURL(schedule_id);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"success\":\"true\"}";
        // -------------- PROCESS RESPONSE --------------
        JSONObject jsonData = new JSONObject(APIresponse);
        if (jsonData.getBoolean("success")){
            Log.v("RequestHandeler", "Add to favorite succesful");
            return true;
        }
        else {
            Log.v("RequestHandeler","Add to favorite failed");
            return false;
        }
    }

    public static boolean deleteScheduleByIdRequest(String schedule_id) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getScheduleDeleteURL(schedule_id);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"success\":\"true\"}";
        // -------------- PROCESS RESPONSE --------------
        JSONObject jsonData = new JSONObject(APIresponse);
        if (jsonData.getBoolean("success")){
            Log.v("RequestHandeler", "Delete schedule succesful");
            return true;
        }
        else {
            Log.v("RequestHandeler","Delete schedule failed");
            return false;
        }
    }

    public static JSONObject readCourseListByScheduleIdRequest(String schedule_id) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getCourseListByScheduleURL(schedule_id);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse ="{\"cursos\":[{\"codigo\":\"CSB01\",\"secciones\":[{\"seccion\":\"01\",\"labs\":[{\"id\":\"1\",\"numero\":\"01\",\"docente_nombre\":\"Marvin\",\"docente_apellido\":\"Abisrror Zarate\",\"inscrito\":\"true\"}],\"teorias\":[{\"id\":\"2\",\"numero\":\"00\",\"docente_nombre\":\"Marvin\",\"docente_apellido\":\"Abisrror Zarate\",\"inscrito\":\"true\"}],\"teorias_virtuales\":[]},{\"seccion\":\"02\",\"labs\":[{\"id\":\"3\",\"numero\":\"01\",\"docente_nombre\":\"Marvin\",\"docente_apellido\":\"Abisrror Zarate\",\"inscrito\":\"false\"}],\"teorias\":[{\"id\":\"4\",\"numero\":\"00\",\"docente_nombre\":\"Marvin\",\"docente_apellido\":\"Abisrror Zarate\",\"inscrito\":\"false\"}],\"teorias_virtuales\":[]}]}]}";
        // -------------- PROCESS RESPONSE --------------
        return new JSONObject(APIresponse);
    }

    public static JSONObject addClasToScheduleByIdRequest(String schedule_id, String clas_id) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getScheduleUpdateAddClassURL(schedule_id);
        // ---------------- SUBMIT JSON -----------------
        JSONObject submitJSON = new JSONObject();
        submitJSON.put("clase_id", clas_id);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON response
        String APIresponse = "{\"pending_cursos\":\"CS2B01\",\"status_horario\":\"Pending\",\"success\":true,\"table_horario\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"]]}";
        // -------------- PROCESS RESPONSE --------------
        return new JSONObject(APIresponse);
    }

    public static JSONObject deleteClasFromScheduleByIdRequest(String schedule_id, String clas_id) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getScheduleUpdateDeleteClassURL(schedule_id);
        // ---------------- SUBMIT JSON -----------------
        JSONObject submitJSON = new JSONObject();
        submitJSON.put("clase_id", clas_id);
        // ---------------- API RESPONSE ----------------
        // Sample API JSON repsonse
        String APIresponse = "{\"pending_cursos\":\"CS2B01\",\"status_horario\":\"Pending\",\"success\":true,\"table_horario\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"CS2B01\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\"]]}";
        return new JSONObject(APIresponse);
    }

    public static boolean loginRequest(String id_input, String password_input) throws JSONException {
        // -------------------- URL ---------------------
        String url = ConnectionUtils.getLoginUrl();
        // ---------------- SUBMIT JSON -----------------
        JSONObject submitJSON = new JSONObject();
        submitJSON.put("codigo", id_input);
        submitJSON.put("password", password_input);
        // ---------------- API RESPONSE ----------------
        // Sample APIresponse
        String APIresponse;
        if (id_input.equals("202010387") & password_input.equals("sample")) {
            APIresponse = "{\"success\":\"true\",\"id\":\"202010387\",\"nombre\":\"Rodrigo Gabriel\",\"apellido\":\"Salazar Alva\"}";
        } else{
            APIresponse =  "{\"success\":\"false\"}";
        }
        JSONObject jsonData = new JSONObject(APIresponse);
        // ------------- PROCESS RESPONSE --------------
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
}
