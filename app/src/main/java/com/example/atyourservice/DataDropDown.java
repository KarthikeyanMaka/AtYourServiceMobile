package com.example.atyourservice;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataDropDown {
    public static void BindHosScreenStates(String Jsonresult, Spinner dropdown, Context c) throws JSONException {
        try {

            JSONArray ja = new JSONArray(Jsonresult);
            JSONObject jo;

            List<String> stateCodelist =new ArrayList<String>();
            stateCodelist.add("");

            for (int i=0;i<ja.length();i++) {
                jo = ja.getJSONObject(i);

                stateCodelist.add(jo.getString("stateCode") +","+jo.getString("stateName"));

            }

            String[] stateArray = stateCodelist.toArray(new String[stateCodelist.size()]);

            ArrayAdapter<String> jsonlist= new ArrayAdapter<String>(c,R.layout.activity_covid_hospitals,stateArray);

            dropdown.setAdapter(jsonlist);

        } catch (Exception ex) {
            throw ex;
        }
    }
}
