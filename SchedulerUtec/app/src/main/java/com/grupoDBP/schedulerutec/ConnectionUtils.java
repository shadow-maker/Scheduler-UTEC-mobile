package com.grupoDBP.schedulerutec;

public class ConnectionUtils {
    private static final String BASE_API_URL = "http://127.0.0.1:8888/api";

    public static String getStudentReadURL(String horario_id){
        return BASE_API_URL+"/alumnos/"+horario_id;
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
}
