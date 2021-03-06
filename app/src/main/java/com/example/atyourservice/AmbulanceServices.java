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

public class AmbulanceServices extends AppCompatActivity {
    Spinner dropdown;
    Spinner stateDrop;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_services);
        c=AmbulanceServices.this;

        try {
            Locale Current = getResources().getConfiguration().locale;
            //Language dropdown configuration
            dropdown = (Spinner) findViewById(R.id.spnamblang);
            final DropDown langDropDown = new DropDown(dropdown, this,Current.getLanguage());

            Button home= (Button)this.findViewById(R.id.btn_home);
            Button health= (Button)this.findViewById(R.id.btn_Health);
            Button emer= (Button)this.findViewById(R.id.btn_Emer);
            Button dash= (Button)this.findViewById(R.id.btn_local);
            Common.setMenuColor(home,health,emer,dash,"Emer",this);

            Button intMenu = (Button)this.findViewById(R.id.btn_emer_ambulance);
            Common.SetInternalMenuColor(intMenu);

            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

            //State Drop Down Configuration
            String StateList = LocationFinder.ServerRequest(Common.API_SERVER+"GetAllState");
            stateDrop = (Spinner) findViewById(R.id.dpambstate);
            GridBinder.BindStateDropDown(this, StateList, stateDrop);

            stateDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long id) {

                    if (adapterView.getItemAtPosition(postion).toString() != "") {
                        try {
                            LoadAmbulanceGrid(adapterView.getItemAtPosition(postion).toString().split(",")[0].toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage(),this);
        }


    }
    public void LoadAmbulanceGrid(String StateCode) throws JSONException {
        try{
            String Url = Common.API_SERVER+"GetStateWiseAmbHelpline/" + StateCode;
            String result = LocationFinder.ServerRequest(Url);
            //ErrorHandling.ErrorDialog(result+Url,c);

            if (result != "") {
                GridView objGrid = (GridView) findViewById(R.id.gvamb);
                GridBinder.BindAmbGrid(c, result, objGrid);
            }


        }
        catch (Exception ex){
            throw ex;
        }
    }

    public void Setlocale(String localName){
        Locale Current = getResources().getConfiguration().locale;
        Locale appLocale = new Locale(localName);

        if(Current.getLanguage() !=appLocale.getLanguage()){

            Configuration conf = new Configuration();
            conf.locale=appLocale;

            getBaseContext().getResources().updateConfiguration(conf,getBaseContext().getResources().getDisplayMetrics());

            Intent refresh = new Intent(this , AmbulanceServices.class);
            finish();
            startActivity(refresh);
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
        Intent intent = new Intent(this, CovidLabs.class);
        startActivity(intent);
    }
    public void GotoAmbulance(View v)
    {
        //Intent intent = new Intent(this, AmbulanceServices.class);
        //startActivity(intent);
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

}