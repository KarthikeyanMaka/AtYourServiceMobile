package com.example.atyourservice;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.ErrorHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class GridBinder {
    public static void BindHelplineGrid (Context c, String jsonData, GridView gv,boolean isCentral) throws JSONException {
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;
            StateHelplinelist objhelplst = new StateHelplinelist();
            
            ArrayList<StateHelplinelist> jsonlist= new ArrayList<StateHelplinelist>();


            jsonlist.clear();

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String helpline;
                String helpemail;
                String tollfree;

                if(isCentral) {
                    helpline = jo.getString("globalhelpline_number");
                    helpemail = "";
                    tollfree = jo.getString("toll_free");
                }
                else{
                    helpline = jo.getString("helpline_number");
                    helpemail = jo.getString("helpline_email");
                    tollfree = jo.getString("toll_free");
                }

                objhelplst.helpline_email= helpemail;
                objhelplst.helpline_number=helpline;
                objhelplst.toll_free=tollfree;

                jsonlist.add(objhelplst);

            }


            GridAdapter objHelpView = new GridAdapter(c,jsonlist,isCentral);


            gv.setAdapter(objHelpView);


            }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public static void BindStateDropDown (Context c, String jsonData, Spinner gv) throws JSONException {
        try {

            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            List<String> stateCodelist =new ArrayList<String>();
            stateCodelist.add("");

            for (int i=0;i<ja.length();i++) {
                jo = ja.getJSONObject(i);

                stateCodelist.add(jo.getString("stateCode") +","+jo.getString("stateName"));

            }

            String[] stateArray = stateCodelist.toArray(new String[stateCodelist.size()]);

            ArrayAdapter<String> jsonlist= new ArrayAdapter<String>(c,R.layout.support_simple_spinner_dropdown_item,stateArray);

            gv.setAdapter(jsonlist);

        } catch (Exception ex) {
            throw ex;
        }
    }
}
