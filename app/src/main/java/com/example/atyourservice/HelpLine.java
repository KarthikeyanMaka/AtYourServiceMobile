package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.concurrent.ExecutionException;

import appcommon.Common;
import appcommon.DropDown;
import appcommon.ErrorHandling;
import appcommon.GridBinder;
import appcommon.JsonAsyncTask;

public class HelpLine extends AppCompatActivity {
    Spinner dropdown;
    Spinner stateDrop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_line);
        try {

            Button home= (Button)this.findViewById(R.id.btn_home);
            Button health= (Button)this.findViewById(R.id.btn_Health);
            Button emer= (Button)this.findViewById(R.id.btn_Emer);
            Button dash= (Button)this.findViewById(R.id.btn_local);
            Common.setMenuColor(home,health,emer,dash,"Emer",this);


            //CentralHelpline Config
            String CentralJsonData ="["+ServerRequest("https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetCentralHelpline")+"]";
            GridView objCentGrid = (GridView) findViewById(R.id.gvCenthelp);
            GridBinder.BindHelplineGrid(this,CentralJsonData,objCentGrid,true);

            //State Drop Down Configuration
            String StateList= ServerRequest("https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllState");
            stateDrop =(Spinner)findViewById(R.id.dpState);
            GridBinder.BindStateDropDown(this, StateList,stateDrop);

            if(savedInstanceState !=null){
                stateDrop.setSelection(savedInstanceState.getInt("StateSpinner",0));
            }


        stateDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long id) {

                if (adapterView.getItemAtPosition(postion).toString() != "") {
                    LoadStateHelplineGrid(adapterView.getItemAtPosition(postion).toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Language dropdown configuration
        dropdown =(Spinner)findViewById(R.id.spinner1);
        final DropDown langDropDown = new DropDown(dropdown, this);


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




        }
//        catch (JSONException e) {
//            ErrorHandling.ErrorDialog(e.getMessage().toString(),this);
//        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage().toString(),this);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt("StateSpinner",stateDrop.getSelectedItemPosition());
        super.onSaveInstanceState(outState);

    }


    public void Setlocale(String localName){
        Locale Current = getResources().getConfiguration().locale;
        Locale appLocale = new Locale(localName);

        if(Current !=appLocale){

            Configuration conf = new Configuration();
            conf.locale=appLocale;

            getBaseContext().getResources().updateConfiguration(conf,getBaseContext().getResources().getDisplayMetrics());

            Intent refresh = new Intent(this , HelpLine.class);
            finish();
            startActivity(refresh);
        }

    }
    public String ServerRequest(String URL)
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
    public String ConstructHelplineURL(String URL, String StateCodeName)
    {
        String result = "";

        String StateCode = StateCodeName.split(",")[0].toString();

        URL=URL.replace("<StateCode>",StateCode);
        result =ServerRequest(URL);
        return result;
    }
    public void LoadStateHelplineGrid(String StateCodeName)
    {
        String JsonData = ConstructHelplineURL("https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetHelplinebyState/<StateCode>"
                , StateCodeName);

        //State Helpline config
        GridView objGrid = (GridView) findViewById(R.id.gvhelp);
        try {
            GridBinder.BindHelplineGrid(getApplicationContext(), JsonData, objGrid, false);
        } catch (JSONException e) {
            ErrorHandling.ErrorDialog(e.getMessage().toString(), getApplicationContext());
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
    public void GotoHepline(View v)
    {
       // Intent intent = new Intent(this, HelpLine.class);
        //startActivity(intent);
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
        Intent intent = new Intent(this, AmbulanceServices.class);
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