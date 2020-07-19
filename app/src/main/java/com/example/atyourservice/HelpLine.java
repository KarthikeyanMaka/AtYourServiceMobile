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

public class HelpLine extends AppCompatActivity {
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_line);
        try {

        String JsonData ="[{\"helpline_number\":\"044-29510500\",\"state_or_UT\":\"Tamil Nadu\",\"helpline_email\":\"ncov2019@gov.in\",\"globalhelpline_number\":\"+91-11-23978046\",\"source\":\"https://www.mohfw.gov.in/pdf/coronvavirushelplinenumber.pdf\",\"toll_free\":\"1075\"}]";

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


        //Default Grid config
        GridView objGrid = (GridView) findViewById(R.id.gvhelp);
        GridBinder.BindHelplineGrid(this,JsonData,objGrid);




        }
//        catch (JSONException e) {
//            ErrorHandling.ErrorDialog(e.getMessage().toString(),this);
//        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage().toString(),this);
        }


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
}