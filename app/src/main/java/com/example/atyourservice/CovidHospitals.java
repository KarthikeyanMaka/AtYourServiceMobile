package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

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
    Spinner dropdown;
    Spinner stateDrop;
    Spinner cityDrop;
    String currentStateCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_hospitals);

        try{
          //State Drop Down Configuration
            String StateList= ServerRequest("https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllState");
            stateDrop =(Spinner)findViewById(R.id.dpStateHos);
            cityDrop =(Spinner)findViewById(R.id.dpCity);
            GridBinder.BindStateDropDown(this, StateList,stateDrop);



            stateDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long id) {
                    if (adapterView.getItemAtPosition(postion).toString() != "") {
                        try {
                            String StateCode= adapterView.getItemAtPosition(postion).toString();
                            StateCode = StateCode.split(",")[0].toString();
                            currentStateCode= StateCode;
                            ErrorHandling.AlertMessage(currentStateCode,getApplicationContext());
                            LoadCityData(StateCode);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ErrorHandling.ErrorDialog(e.getMessage(),getApplicationContext());
                        }
                        catch (Exception ex){
                            ErrorHandling.ErrorDialog(ex.getMessage(),getApplicationContext());
                        }

                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
/*
            cityDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long id) {
                    if (adapterView.getItemAtPosition(postion).toString() != "") {
                        LoadHospitalData(currentStateCode, adapterView.getItemAtPosition(postion).toString());
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

*/
        }
        catch (JSONException e) {
          e.printStackTrace();
        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage().toString(),this);
        }
    }
    public void LoadCityData(String StateCode) throws JSONException {
        try {
            String Citylist = ServerRequest("https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllStateCity/" + StateCode);
            ErrorHandling.AlertMessage(Citylist, getApplicationContext());
            GridBinder.BindStateDropDown(getApplicationContext(), Citylist, cityDrop);
        }
        catch (JSONException ex)
        {
            ErrorHandling.ErrorDialog(ex.getMessage(),this);
        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage(), this);
        }

    }
    private void LoadHospitalData( String State,String City){

        String url = "https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetHospitals/"+State+"/"+City;

        String JsonData = ServerRequest(url);

        //State Helpline config
        GridView objGrid = (GridView) findViewById(R.id.gvhelp);
        try {
            GridBinder.BindHelplineGrid(getApplicationContext(), JsonData, objGrid, false);
        } catch (JSONException e) {
            ErrorHandling.ErrorDialog(e.getMessage().toString(), getApplicationContext());
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
    public void Setlocale(String localName){
        Locale Current = getResources().getConfiguration().locale;
        Locale appLocale = new Locale(localName);

        if(Current !=appLocale){

            Configuration conf = new Configuration();
            conf.locale=appLocale;

            getBaseContext().getResources().updateConfiguration(conf,getBaseContext().getResources().getDisplayMetrics());

            Intent refresh = new Intent(this , CovidHospitals.class);
            finish();
            startActivity(refresh);
        }

    }
}