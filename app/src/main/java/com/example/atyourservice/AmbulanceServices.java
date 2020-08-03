package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AmbulanceServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_services);
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
}