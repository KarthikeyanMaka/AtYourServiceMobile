package com.example.atyourservice;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.LayoutRes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class GridBinder {
    public static void BindHelplineGrid (Context c, String jsonData, GridView gv) throws JSONException {
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;
            StateHelplinelist objhelplst = new StateHelplinelist();
            
            ArrayAdapter<StateHelplinelist> jsonlist= new ArrayAdapter<StateHelplinelist>(c,R.layout.activity_help_line);
            jsonlist.clear();

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                String helpline=jo.getString("helpline_number");
                String helpemail=jo.getString("helpline_email");
                String tollfree=jo.getString("toll_free");

                objhelplst.helpline_email= helpemail;
                objhelplst.helpline_number=helpline;
                objhelplst.toll_free=tollfree;

                jsonlist.add(objhelplst);

            }
            gv.setAdapter(jsonlist);

        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
