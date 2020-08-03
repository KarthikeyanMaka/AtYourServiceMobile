package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.Locale;

import appcommon.DropDown;
import appcommon.ErrorHandling;

public class Emergency extends AppCompatActivity {

    private boolean userInteract;
    String CurrentLanguage= "en", currentLang;
    Spinner dropdown;
    final Context objContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        try {

            //Language dropdown configuration
            dropdown = findViewById(R.id.spinner1);
            final DropDown langDropDown = new DropDown(dropdown, this);


            //Adding scroll bars
            final ScrollView scrollView = (ScrollView) findViewById(R.id.Scroll1);
            scrollView.setVerticalScrollbarPosition(ScrollView.SCROLLBAR_POSITION_RIGHT);
            scrollView.setVerticalScrollBarEnabled(true);


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
            ImageView img = (ImageView) findViewById(R.id.imghelpline);
            img.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Emergency.this, HelpLine.class);
                        startActivity(intent);
                    } catch (Exception ex) {
                        ErrorHandling.ErrorDialog(ex.getMessage().toString(), Emergency.this);
                    }
                }
            });

            ImageView imghos = (ImageView) findViewById(R.id.imagehospView);
            imghos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent hosintent = new Intent(Emergency.this, CovidHospitals.class);
                    startActivity(hosintent);

                }
            });

            ImageView imglabs =(ImageView) findViewById(R.id.imgtestlabs);
            imglabs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent labsintent = new Intent(Emergency.this, CovidLabs.class);
                    startActivity(labsintent);
                }
            });

            ImageView imgAmbula =(ImageView) findViewById(R.id.imgambulance);
            imgAmbula.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ambulintent = new Intent(Emergency.this,AmbulanceServices.class);
                    startActivity(ambulintent);
                }
            });



        }
        catch (Exception ex)
        {
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

            Intent refresh = new Intent(this , Emergency.class);
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

    }
    public void GotoLocal(View v)
    {
        Intent intent = new Intent(this, CovidDashboard.class);
        startActivity(intent);
    }
}