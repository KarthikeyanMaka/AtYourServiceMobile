package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.savedstate.SavedStateRegistryOwner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import org.json.JSONException;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CovidHospitals extends AppCompatActivity {
    Spinner dplang;
    Spinner dphosstate;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_hospitals);

        c= CovidHospitals.this;
        String StateList= ServerRequest("https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllState");
        dphosstate = (Spinner) findViewById(R.id.dphosstate);
        try {
            GridBinder.BindStateDropDown(CovidHospitals.this, StateList,dphosstate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dphosstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).toString() != "") {
                    ErrorHandling.ErrorDialog("tocheck",c);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }
    private String ServerRequest(String URL)
    {
        String result="";

        JsonAsyncTask getRequest = new JsonAsyncTask();
        try {
            result = getRequest.execute(URL).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}