package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ThreadPoolExecutor;

import appcommon.AppNotification;
import appcommon.Common;
import appcommon.DropDown;
import appcommon.ErrorHandling;
import appcommon.LangLocModel;
import appcommon.LocationFinder;
import appcommon.RecoveredCountsModel;
import appcommon.VaccineModel;

import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;


public class HomeActivity extends AppCompatActivity {
    String defLang;
    String city;
    String State;
    LangLocModel objCityState = null;
    ImageView imageView;
    Integer[] imgList = new Integer[]{R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,
            R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10,R.drawable.a11};
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
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;
    int notdelay=500000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(!LocationFinder.isNetworkStatusAvailable(this)){
            ErrorHandling.AlertMessage(getString(R.string.app_networkmsg), this);
        }
        //AppNotification.NotificationBuildShow(this);

        //menu color change
        Button home= (Button)findViewById(R.id.btn_home);
        Button health= (Button)findViewById(R.id.btn_Health);
        Button emer= (Button)findViewById(R.id.btn_Emer);
        Button dash= (Button)findViewById(R.id.btn_local);
        Common.setMenuColor(home,health,emer,dash,"Home",this);

        Locale Current = getResources().getConfiguration().locale;

        //Language dropdown configuration
        dropdown = findViewById(R.id.spinner1);
        final DropDown langDropDown = new DropDown(dropdown, this,Current.getLanguage());

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
        } catch (JSONException | IOException e) {
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
                if(objCounts.districtName != null){
                    String message =getString(R.string.app_districtName)+":"+objCounts.districtName;
                    txtcurrDis.setText(message);
                }
            }

        }

        imageView = (ImageView)findViewById(R.id.imageView);
        Glide.with(this).load(imgList[count]).into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>3) {
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
      //  txthomelocal = (TextView)findViewById(R.id.txthomelocal);
        txthomeemer = (TextView)findViewById(R.id.txthomemergency);
        txthomeother = (TextView)findViewById(R.id.txthomeothers);

        txthomeemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoEmer(view);
            }
        });

     /*   txthomelocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoLocal(view);
            }
        });
*/
        txthomehealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoHealth(view);
            }
        });

        txthomeother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoVaccine(view);
            }
        });

        //link blink
        Common.TextBlink(txthomehealth);
        Common.TextBlink(txthomeemer);


//        Button home = (Button) findViewById(R.id.btn_home);
//        home.setBackgroundColor(Color.GREEN);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        try {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.settings_menu, menu);
        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage(),this);
        }
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        try{
            int id= item.getItemId();
            if(id ==R.id.action_settings){
                Intent intent= new Intent(HomeActivity.this,SettingsActivity.class);
                startActivity(intent);
                return true;
            }

        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage(),this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void delayprocess()
    {

    }
    public void GotoHome(View v)
    {

    }
    public void GotoVaccine(View v)
    {

        Intent intent = new Intent(this, CovidVaccine.class);
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
    @Override
    protected void onResume() {
        try {
            handler.postDelayed(runnable = new Runnable() {
                public void run() {
                    handler.postDelayed(runnable, delay);
                    if (count > 3) {
                        count = 0;
                    } else {
                        count++;
                    }

                    Glide.with(getApplicationContext()).load(imgList[count]).into(imageView);
                    SendNotification();

                }
            }, delay);
        }
        catch (Exception ex)
        {
            ErrorHandling.ErrorDialog(ex.getMessage().toString(),this);
        }
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
    }
    @Override
    protected void onUserLeaveHint(){
        try {
            handler.postDelayed(runnable = new Runnable() {
                public void run() {
                    handler.postDelayed(runnable, notdelay);
                    if (count > 3) {
                        count = 0;
                    } else {
                        count++;
                    }

                    //Glide.with(getApplicationContext()).load(imgList[count]).into(imageView);
                    SendNotification();

                }
            }, delay);
        }
        catch (Exception ex)
        {
            ErrorHandling.ErrorDialog(ex.getMessage().toString(),this);
        }
        super.onUserLeaveHint();
    }
    public void SendNotification(){
        Common.sendNotification(this);
    }



}

