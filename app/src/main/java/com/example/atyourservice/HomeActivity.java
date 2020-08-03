package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.util.Locale;

import appcommon.DropDown;
import appcommon.ErrorHandling;
import appcommon.LangLocModel;
import appcommon.LocationFinder;
import appcommon.RecoveredCountsModel;

public class HomeActivity extends AppCompatActivity {
    String defLang;
    String city;
    String State;
    LangLocModel objCityState = null;
    ImageView imageView;
    Integer[] imgList = new Integer[]{R.drawable.giphy,R.drawable.washhans,R.drawable.face};
    TextView txtRecoveredCount;
    TextView txtActiveount;
    TextView txtDeceasedCount;
    TextView txtTotCount;
    TextView txthomehealth;
    TextView txthomeemer;
    TextView txthomeother;
    TextView txthomelocal;
    TextView txtcurrDis;
    Integer count=0;
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //Language dropdown configuration
        dropdown = findViewById(R.id.spinner1);
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


        //To set location permission
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            delayprocess();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 8000);

            }

        } catch (Exception e){
            ErrorHandling.ErrorDialog(e.getMessage(),this);
        }



        try {
            defLang= LocationFinder.getLocationLang(HomeActivity.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }


      //  if(defLang !="")
        //    Setlocale(defLang);

        txtActiveount = (TextView)findViewById(R.id.txtActivecount);
        txtDeceasedCount = (TextView)findViewById(R.id.txtdeceasedcount);
        txtTotCount = (TextView)findViewById(R.id.txttotalcount);
        txtRecoveredCount = (TextView)findViewById(R.id.txtRecoveredcount);
        txtcurrDis = (TextView) findViewById(R.id.txtcurrentdistrict);

        txtActiveount.setText("");
        txtDeceasedCount.setText("");
        txtTotCount.setText("");
        txtRecoveredCount.setText("");
        txtcurrDis.setText("");

        try {
            objCityState=LocationFinder.GetCityState(HomeActivity.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(objCityState !=null){

            //code here for getting current covid data
            RecoveredCountsModel objCounts = null;
            try {
                objCounts=LocationFinder.GetRecoveredData(objCityState.city,objCityState.statecode,HomeActivity.this);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (objCounts !=null)
            {
                txtActiveount.setText(objCounts.active);
                txtDeceasedCount.setText(objCounts.deceased);
                txtTotCount.setText(objCounts.confirmed);
                txtRecoveredCount.setText(objCounts.recovered);
                if(objCounts.districtName != null)
                    txtcurrDis.setText(R.string.app_districtName+":"+objCounts.districtName );
            }

        }

        imageView = (ImageView)findViewById(R.id.imageView);
        Glide.with(this).load(imgList[count]).into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>1) {
                    count=0;
                }
                else
                {
                    count++;
                }

                Glide.with(getApplicationContext()).load(imgList[count]).into(imageView);
            }
        });


        txthomehealth = (TextView)findViewById(R.id.txthomehealth);
        txthomelocal = (TextView)findViewById(R.id.txthomelocal);
        txthomeemer = (TextView)findViewById(R.id.txthomemergency);
        txthomeother = (TextView)findViewById(R.id.txthomeothers);

        txthomeemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoEmer(view);
            }
        });

        txthomelocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoLocal(view);
            }
        });

        txthomehealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoHealth(view);
            }
        });


//        Button home = (Button) findViewById(R.id.btn_home);
//        home.setBackgroundColor(Color.GREEN);
    }
    public void delayprocess()
    {

    }
    public void GotoHome(View v)
    {

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

        if(Current.getLanguage() !=appLocale.getLanguage()){

            Configuration conf = new Configuration();
            conf.locale=appLocale;

            getBaseContext().getResources().updateConfiguration(conf,getBaseContext().getResources().getDisplayMetrics());

            Intent refresh = new Intent(this , HomeActivity.class);
            finish();
            startActivity(refresh);
        }

    }
}