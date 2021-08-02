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
}
