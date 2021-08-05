package com.grupoDBP.schedulerutec;

public class ConnectionUtils {
    private static  final String BASE_WEB_URL = "http://127.0.0.1:8888";
    private static final String BASE_API_URL = "http://127.0.0.1:8888/api";

    // API
    public static String getStudentReadURL(String alumno_id){
        return BASE_API_URL+"/alumnos/read/"+alumno_id;
    }

    public static String getScheduleReadURL(String horario_id){
        return BASE_API_URL+"/horarios/read/"+horario_id;
    }

    public static String getAllSheduleReadURL() {
        return BASE_API_URL+"/horarios/read";
    }

    public static String getScheduleCreateURL(){
        return BASE_API_URL+"/horarios/create";
    }

    public static String getScheduleUpdateRenameURL(String horario_id){
        return BASE_API_URL+"/horarios/update/"+horario_id+"/rename";
    }

    public static String getFavoriteDeleteURL(String horario_id){
        return BASE_API_URL + "/favoritos/delete/"+horario_id;
    }

    public static String getFavoriteAddURL(String horario_id){
        return BASE_API_URL + "/favoritos/add/"+horario_id;
    }

    public static String getScheduleDeleteURL(String horario_id){
        return BASE_API_URL+"/horarios/delete"+horario_id;
    }

    public static String getCourseListByScheduleURL(String horario_id){
        return  BASE_API_URL + "/cursos/read/por-horario/"+horario_id;
    }

    public static String getScheduleUpdateAddClassURL(String horario_id){
        return BASE_API_URL+"/horarios/update/"+horario_id+"/add-clase";
    }

    public static String getScheduleUpdateDeleteClassURL(String horario_id){
        return BASE_API_URL+"/horarios/update/"+horario_id+"/delete-clase";
    }

    // WEB
    public static String getLoginURL(){
        return BASE_WEB_URL + "/auth/login";
    }

    public static String getRegisterURL(){
        return BASE_WEB_URL + "/auth/register";
    }

    public static String getEditOwnProfileURL() { return BASE_WEB_URL + "/alumnos/" + SessionData.userId + "/update"; }

    public static String getDeleteOwnProfileURL() { return BASE_WEB_URL + "/alumnos/" + SessionData.userId + "/delete"; }

}
