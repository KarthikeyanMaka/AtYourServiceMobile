package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import org.json.JSONException;

import java.util.Locale;

import appcommon.Common;
import appcommon.DropDown;
import appcommon.ErrorHandling;
import appcommon.GridBinder;
import appcommon.LocationFinder;

public class CovidDashboard extends AppCompatActivity {
    Spinner dplang;
    Spinner dphosstate;
    Spinner dphosdist;
    Context c;
    String currentState="";
    String currentDist="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_dashboard);

        try {
            c = CovidDashboard.this;
            //Bind State Drop down
            String StateList = LocationFinder.ServerRequest("https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllState");
            dphosstate = (Spinner) findViewById(R.id.dpcovidstate);
            try {
                GridBinder.BindStateDropDown(CovidDashboard.this, StateList, dphosstate);
            } catch (Exception e) {
                e.printStackTrace();
            }


            //menu color change
            Button home= (Button)this.findViewById(R.id.btn_home);
            Button health= (Button)this.findViewById(R.id.btn_Health);
            Button emer= (Button)this.findViewById(R.id.btn_Emer);
            Button dash= (Button)this.findViewById(R.id.btn_local);
            Common.setMenuColor(home,health,emer,dash,"Dash",this);

            dphosdist = (Spinner) findViewById(R.id.dpcoviddist);

            dphosstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (adapterView.getItemAtPosition(i).toString() != "") {
                        currentState = adapterView.getItemAtPosition(i).toString().split(",")[0].toString();
                        loadDistrict(currentState);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            dphosdist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (adapterView.getItemAtPosition(i).toString() != ""){
                        currentDist=adapterView.getItemAtPosition(i).toString();

                        loadCovidDashboard();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            //Language dropdown configuration
            dplang =(Spinner)findViewById(R.id.dplangu);
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
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage(),c);
        }

//
//        Button home = (Button) findViewById(R.id.btn_local);
//        home.setBackgroundColor(Color.GREEN);
    }
    public void loadDistrict(String pcurrentState){
        try{
            String Url = "https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllStateDistrict/" +pcurrentState;

            String citylist = LocationFinder.ServerRequest(Url);
            GridBinder.BindCityDropDown(c, citylist,dphosdist);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void loadCovidDashboard(){
        try{
            try{
                String Url = "https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetRecoveredCountDist/"+currentState+"/"+currentDist;
                String result = LocationFinder.ServerRequest(Url);

                if(result !=""){
                    GridView objGrid = (GridView) findViewById(R.id.gvcoviddash);
                    GridBinder.BindCovidDashGrid(c,result,objGrid);
                }

            } catch (JSONException e) {
                ErrorHandling.ErrorDialog(e.getMessage(), c);
            }

        }
        catch (Exception ex){
            throw ex;
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

    }
    public void Setlocale(String localName) {
        Locale Current = getResources().getConfiguration().locale;
        Locale appLocale = new Locale(localName);

        if (Current != appLocale) {

            Configuration conf = new Configuration();
            conf.locale = appLocale;

            getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());

            Intent refresh = new Intent(c, CovidDashboard.class);
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