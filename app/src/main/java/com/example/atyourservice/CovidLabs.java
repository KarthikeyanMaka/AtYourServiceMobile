package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import org.json.JSONException;

import java.util.Locale;

import appcommon.DropDown;
import appcommon.ErrorHandling;
import appcommon.GridBinder;
import appcommon.LocationFinder;

public class CovidLabs extends AppCompatActivity {
    Spinner dplang;
    Spinner dplabstate;
    Spinner dplabscity;
    Context c;
    String currentState="";
    String currentCity="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_labs);

        try {

            c = CovidLabs.this;

            String StateList = LocationFinder.ServerRequest("https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllState");
            dplabstate = (Spinner) findViewById(R.id.dplabstate);
            try {
                GridBinder.BindStateDropDown(CovidLabs.this, StateList, dplabstate);
            } catch (Exception e) {
                e.printStackTrace();
            }

            dplabscity = (Spinner) findViewById(R.id.dplabcity);

            dplabstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (adapterView.getItemAtPosition(i).toString() != "") {
                        currentState = adapterView.getItemAtPosition(i).toString().split(",")[0].toString();
                        loadCity(currentState);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            dplabscity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (adapterView.getItemAtPosition(i).toString() != "") {
                        currentCity = adapterView.getItemAtPosition(i).toString();

                        try {
                            loadlabsGrid();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //Language dropdown configuration
            dplang = (Spinner) findViewById(R.id.spnlnglabs);
            final DropDown langDropDown = new DropDown(dplang, c);

            dplang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if (parentView.getItemAtPosition(position).toString() != "") {
                        Setlocale(langDropDown.ReturnSelectedLocale(parentView.getItemAtPosition(position).toString()));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }
        catch (Exception ex)
        {
            ErrorHandling.ErrorDialog(ex.getMessage(),c);
        }
    }
    private void loadCity(String StateCode){
        try{
            String Url = "https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllStateCity/" +StateCode;

            String citylist = LocationFinder.ServerRequest(Url);
            GridBinder.BindCityDropDown(c, citylist,dplabscity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadlabsGrid() throws JSONException {
        try {
            String Url = "https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetResource/" + currentState + "/" + currentCity;
            String result = LocationFinder.ServerRequest(Url);
            //ErrorHandling.ErrorDialog(result+Url,c);

            if (result != "") {
                GridView objGrid = (GridView) findViewById(R.id.gvcovidlabs);
                GridBinder.BindCovidLabGrid(c, result, objGrid);
            }


        } catch (Exception ex) {
            throw ex;
        }
    }

    public void GotoHepline(View v)
    {
        Intent intent = new Intent(this, HelpLine.class);
        startActivity(intent);
    }
    public void GotoHospitals(View v)
    {
        Intent intent = new Intent(this, CovidHospitals.class);
        startActivity(intent);
    }
    public void GotoLabs(View v)
    {
        //Intent intent = new Intent(this, CovidLabs.class);
        //startActivity(intent);
    }
    public void GotoAmbulance(View v)
    {
        Intent intent = new Intent(this, AmbulanceServices.class);
        startActivity(intent);
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
    public void Setlocale(String localName){
        Locale Current = getResources().getConfiguration().locale;
        Locale appLocale = new Locale(localName);

        if(Current !=appLocale){

            Configuration conf = new Configuration();
            conf.locale=appLocale;

            getBaseContext().getResources().updateConfiguration(conf,getBaseContext().getResources().getDisplayMetrics());

            Intent refresh = new Intent(c , CovidLabs.class);
            finish();
            startActivity(refresh);
        }

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