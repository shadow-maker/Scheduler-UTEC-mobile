package com.grupoDBP.schedulerutec;

public class ConnectionUtils {
    private static  final String BASE_WEB_URL = "http://127.0.0.1:8888/";
    private static final String BASE_API_URL = "http://127.0.0.1:8888/api";

    public static String getStudentReadURL(String alumno_id){
        return BASE_API_URL+"/alumnos/"+alumno_id;
    }

    public static String getScheduleReadURL(String horario_id){
        return BASE_API_URL+"/horarios/"+horario_id;
    }

    public static String getScheduleCreateURL(String horario_id){
        return BASE_API_URL+"/horarios/create";
    }

    public static String getScheduleDeleteURL(String horario_id){
        return BASE_API_URL+"/horarios/delete"+horario_id;
    }

    public static String getScheduleUpdateRenameURL(String horario_id){
        return BASE_API_URL+"/horarios/update/"+horario_id+"/rename";
    }

    public static String getScheduleUpdateAddClassURL(String horario_id){
        return BASE_API_URL+"/horarios/update/"+horario_id+"/add-clase";
    }

    public static String getScheduleUpdateDeleteClassURL(String horario_id){
        return BASE_API_URL+"/horarios/update/"+horario_id+"/delete-clase";
    }

    public static String getRegisterUrl(){
        return BASE_WEB_URL + "/auth/register";
    }

    public static String getLoginUrl(){
        return BASE_WEB_URL + "/auth/login";
    }

    public static String getEditOwnProfile() {
        return BASE_WEB_URL + "/alumnos/" + SessionData.userId + "/update";
    }

    public static String getDeleteOwnProfile() {
        return BASE_WEB_URL + "/alumnos/" + SessionData.userId + "/delete";
    }

}
