package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {
    String defLang;
    String city;
    String State;
    LangLocModel objCityState = null;
    ImageView imageView;
    Integer[] imgList = new Integer[]{R.drawable.giphy,R.drawable.washhans};
    TextView txtRecoveredCount;
    TextView txtActiveount;
    TextView txtDeceasedCount;
    TextView txtTotCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //To set location permission
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            ErrorHandling.ErrorDialog(e.getMessage(),this);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    delayprocess();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 3000);

        try {
            defLang= LocationFinder.getLocationLang(HomeActivity.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            objCityState=LocationFinder.GetCityState(HomeActivity.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(objCityState !=null){

            //code here for getting current covid data

        }

        imageView = (ImageView)findViewById(R.id.imageView);
        Glide.with(this).load(imgList[0]).into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getApplicationContext()).load(imgList[1]).into(imageView);
            }
        });

        txtActiveount = (TextView)findViewById(R.id.txtActivecount);
        txtDeceasedCount = (TextView)findViewById(R.id.txtdeceasedcount);
        txtTotCount = (TextView)findViewById(R.id.txttotalcount);
        txtRecoveredCount = (TextView)findViewById(R.id.txtRecoveredcount);

        txtActiveount.setText("1200");
        txtDeceasedCount.setText("100");
        txtTotCount.setText("1500");
        txtRecoveredCount.setText("200");





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
        Intent intent = new Intent(this, Local.class);
        startActivity(intent);
    }
}