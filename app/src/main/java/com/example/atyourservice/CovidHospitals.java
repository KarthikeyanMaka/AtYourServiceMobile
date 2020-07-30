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
    Spinner dphoscity;
    Context c;
    String currentState="";
    String currentCity="";
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

        dphoscity= (Spinner) findViewById(R.id.dphoscity);

        dphosstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).toString() != "") {
                    currentState=adapterView.getItemAtPosition(i).toString().split(",")[0].toString();
                   loadCity(currentState);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dphoscity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).toString() != ""){
                    currentCity=adapterView.getItemAtPosition(i).toString();

                    loadHosGrid();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Language dropdown configuration
        dplang =(Spinner)findViewById(R.id.spnlng);
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
    private String ServerRequest(String URL)
    {
        String result="";

        JsonAsyncTask getRequest = new JsonAsyncTask();
        try {
            result = getRequest.execute(URL).get();
        } catch (ExecutionException e) {
          ErrorHandling.ErrorDialog(e.getMessage(), c);
        } catch (InterruptedException e) {
            ErrorHandling.ErrorDialog(e.getMessage(), c);
        }catch (Exception e)
            {ErrorHandling.ErrorDialog(e.getMessage(), c);}


        return result;
    }
    private void loadHosGrid ()
    {
        try{
            String Url = "https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetHospitals/"+currentState+"/"+currentCity;
            String result = ServerRequest(Url);

            if(result !=""){
                GridView objGrid = (GridView) findViewById(R.id.gvcovidhosp);
                GridBinder.BindHospitalGrid(c,result,objGrid);
            }

        } catch (JSONException e) {
            ErrorHandling.ErrorDialog(e.getMessage(), c);
        }

    }
    private void loadCity(String StateCode){
        try{
        String Url = "https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllStateCity/" +StateCode;

        String citylist = ServerRequest(Url);
        GridBinder.BindCityDropDown(c, citylist,dphoscity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Setlocale(String localName){
        Locale Current = getResources().getConfiguration().locale;
        Locale appLocale = new Locale(localName);

        if(Current !=appLocale){

            Configuration conf = new Configuration();
            conf.locale=appLocale;

            getBaseContext().getResources().updateConfiguration(conf,getBaseContext().getResources().getDisplayMetrics());

            Intent refresh = new Intent(c , CovidHospitals.class);
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
        Intent intent = new Intent(this, Local.class);
        startActivity(intent);
    }

}