package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

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

import appcommon.Common;
import appcommon.DropDown;
import appcommon.ErrorHandling;
import appcommon.GridBinder;
import appcommon.LocationFinder;

public class CovidVaccine extends AppCompatActivity {
    Context c;
    Spinner dplang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_vaccine);
        try {
            c = CovidVaccine.this;

            //Language dropdown configuration
            dplang =(Spinner)findViewById(R.id.dpvaclangu);
            final DropDown langDropDown = new DropDown(dplang, c);

            dplang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if (parentView.getItemAtPosition(position).toString() != "") {
                        try {
                            Setlocale(langDropDown.ReturnSelectedLocale(parentView.getItemAtPosition(position).toString()));
                        }
                        catch (Exception ex)
                        {
                            throw ex;
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            LoadVaccineGrid();

        }
        catch (Exception ex)
        {
            ErrorHandling.ErrorDialog(ex.getMessage(),this);
        }

    }
    public void LoadVaccineGrid(){
        try{
            try{
                String Url = Common.API_SERVER+"GetCovidVaccineStatus";
                String result = LocationFinder.ServerRequest(Url);

                if(result !=""){
                    GridView objGrid = (GridView) findViewById(R.id.gvcovidvaccine);
                    GridBinder.BindVaccineGrid(c,result,objGrid);
                }

            } catch (JSONException e) {
                ErrorHandling.ErrorDialog(e.getMessage(), c);
            }

        }
        catch (Exception ex){
            throw ex;
        }

    }

    public void Setlocale(String localName) {
        Locale Current = getResources().getConfiguration().locale;
        Locale appLocale = new Locale(localName);

        if (Current != appLocale) {

            Configuration conf = new Configuration();
            conf.locale = appLocale;

            getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());

            Intent refresh = new Intent(c, CovidVaccine.class);
            finish();
            startActivity(refresh);

        }
    }
    public void GotoHome(View v)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void GotoHealth(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void GotoEmer(View v)
    {
        Intent intent = new Intent(this, Emergency.class);
        startActivity(intent);
    }
    public void GotoLocal(View v)
    {
        Intent intent = new Intent(this, CovidDashboard.class);
        startActivity(intent);
    }
    public void GotoPrivacy(View v){
        Intent intent = new Intent(this, Disclaimer.class);
        intent.putExtra("DisKey","Privacy");
        startActivity(intent);
    }
    public void GotoDisclaimer(View v){
        Intent intent = new Intent(this, Disclaimer.class);
        intent.putExtra("DisKey","Disclaimer");
        startActivity(intent);
    }
    public void GotoTerms(View v){
        Intent intent = new Intent(this, Disclaimer.class);
        intent.putExtra("DisKey","Terms");
        startActivity(intent);
    }
}