package com.grupoDBP.schedulerutec;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ScheduleTableUtils {
    public static ArrayList<ArrayList<View>> getMatrixFromTable(Context context, View v){
        ViewGroup vg = (ViewGroup) v;
        Log.v(context.getClass().getName(), "#N row in table " + vg.getChildCount()+ ". Now loading data ...");

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
        Log.v(context.getClass().getName(), "Succesfully loaded matrix from table");
        return table_matrix;
    }

    public static void loadScheduleTable(Context context, JSONArray schedule_matrix, TableLayout tb) throws JSONException {
        ArrayList<ArrayList<View>> table_matrix = getMatrixFromTable(context, tb);

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

}
