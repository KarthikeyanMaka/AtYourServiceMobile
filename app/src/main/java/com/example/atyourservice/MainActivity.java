package com.example.atyourservice;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.os.Handler;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import java.util.concurrent.ExecutionException;

import appcommon.Common;
import appcommon.DropDown;
import appcommon.ErrorHandling;
import appcommon.GpsTracker;
import appcommon.JsonAsyncTask;
import appcommon.LocationFinder;


public class MainActivity extends AppCompatActivity implements LocationListener {
    private String [] dietVideoIds= new String[5];
    private String [] immVideoIds= new String[5];
    private String [] remVideoIds= new String[5];
    private String [] expVideoIds= new String[5];
    private String [] medVideoIds= new String[5];
    private GpsTracker gpsTracker;
    private int lstDietId=0;
    private int lstImmId=0;
    private int lstRemId=0;
    private int lstExpId=0;
    private int lstMedId=0;
    private TextView txt;
    private boolean userInteract;
    String CurrentLanguage= "en", currentLang;
    String defLang = "en";
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

            //menu color change
            Button home= (Button)this.findViewById(R.id.btn_home);
            Button health= (Button)this.findViewById(R.id.btn_Health);
            Button emer= (Button)this.findViewById(R.id.btn_Emer);
            Button dash= (Button)this.findViewById(R.id.btn_local);
            Common.setMenuColor(home,health,emer,dash,"Health",this);


            if (!LocationFinder.isNetworkStatusAvailable(this)) {
                ErrorHandling.AlertMessage(getString(R.string.app_networkmsg), this);
            } else {



                //To set location permission
                try {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                try {
                                    delayprocess();

                                } catch (Exception e) {
                                    throw e;
                                }
                            }
                        }, 5000);

                    }
                } catch (Exception e) {
                    ErrorHandling.ErrorDialog(e.getMessage(), this);
                }


                defLang = LocationFinder.getLocationLang(MainActivity.this);


//            if(defLang !="")
                //              Setlocale(defLang);



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


                final ScrollView scrollView = (ScrollView) findViewById(R.id.Scroll1);
                scrollView.setVerticalScrollbarPosition(ScrollView.SCROLLBAR_POSITION_RIGHT);
                scrollView.setVerticalScrollBarEnabled(true);

                Locale Current = getResources().getConfiguration().locale;
                CreateLinkPlay(Current.getLanguage());


            }
        }
        catch (Exception ex)
        {
            ErrorHandling.ErrorDialog(ex.getMessage().toString(),this);
        }

    }


        public void Setlocale(String localName){
        Locale Current = getResources().getConfiguration().locale;
        Locale appLocale = new Locale(localName);

            if(Current.getLanguage() !=appLocale.getLanguage()){



             Configuration conf = new Configuration();
             conf.locale=appLocale;

             getBaseContext().getResources().updateConfiguration(conf,getBaseContext().getResources().getDisplayMetrics());

             Intent refresh = new Intent(this , MainActivity.class);
             finish();
             startActivity(refresh);

         }
        }
        public void delayprocess()
        {

        }
        @Override
        public void onUserInteraction(){
            super.onUserInteraction();
            userInteract= true;
        }
        @Override
        public void onSaveInstanceState(Bundle savedInstanceState){
            super.onSaveInstanceState(savedInstanceState);

            savedInstanceState.putString("CurrentLanguage",CurrentLanguage);
        }
        public void GotoHome(View v)
        {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        public void GotoHealth(View v)
        {

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

        public void PlayNexDietVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView1);

            if(dietVideoIds.length!=0) {
                if (lstDietId >= (dietVideoIds.length - 1))
                    lstDietId = 0;
                else
                    lstDietId = lstDietId + 1;

                PlayVideos(view1, dietVideoIds, lstDietId);
            }
        }
        public void PlayNexImmVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView2);

            if(immVideoIds.length !=0) {
                if (lstImmId >= (immVideoIds.length - 1))
                    lstImmId = 0;
                else
                    lstImmId = lstImmId + 1;

                PlayVideos(view1, immVideoIds, lstImmId);
            }
        }
        public void PlayNexRemVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView3);

            if(remVideoIds.length !=0 ) {
                if (lstRemId >= (remVideoIds.length - 1))
                    lstRemId = 0;
                else
                    lstRemId = lstRemId + 1;

                PlayVideos(view1, remVideoIds, lstRemId);
            }
        }
        public void PlayNexExpVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView4);

            if(expVideoIds.length!=0) {
                if (lstExpId >= (expVideoIds.length - 1))
                    lstExpId = 0;
                else
                    lstExpId = lstExpId + 1;

                PlayVideos(view1, expVideoIds, lstExpId);
            }
        }
        public void PlayNexMedVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView5);

            if(medVideoIds.length !=0) {
                if (lstMedId > (medVideoIds.length - 1))
                    lstMedId = 0;
                else
                    lstMedId = lstMedId + 1;

                PlayVideos(view1, medVideoIds, lstMedId);
            }
        }


    public void CreateLinkPlay(String urllng){
        try {
            switch (urllng) {
                case "ta":
                    urllng = "Tamil";
                    break;
                case "en":
                    urllng = "English";
                    break;
                case "tel":
                    urllng = "Telugu";
                    break;
                case "kan":
                    urllng = "Kannada";
                    break;
                case "mal":
                    urllng = "Malayalam";
                    break;
                case "hi":
                    urllng = "Hindi";
                    break;
                case "bn":
                    urllng ="Bengali";
                    break;
                default:
                    urllng = "Tamil";

            }

            final WebView view1 = (WebView) findViewById(R.id.mWebView1);
            final WebView view2 = (WebView) findViewById(R.id.mWebView2);
            final WebView view3 = (WebView) findViewById(R.id.mWebView3);
            final WebView view4 = (WebView) findViewById(R.id.mWebView4);
            final WebView view5 = (WebView) findViewById(R.id.mWebView5);

            String baseurl =Common.API_SERVER+"GetAllYoutubeID/";

            String ImmBoosURL = baseurl+"Immunity Booster/" + urllng + "/Rating/5";
            String DietPlan = baseurl+"Diet Plan/" + urllng + "/Rating/5";
            String HomeMadeRemedies = baseurl+"Home Made Remedies/" + urllng + "/Rating/5";
            String ExpVideos = baseurl+"Covid Experience/" + urllng + "/Views/5";
            String Meditation = baseurl+"meditation/" + urllng + "/Views/5";

            dietVideoIds = LoadVideo(DietPlan, view1);
            immVideoIds = LoadVideo(ImmBoosURL, view2);
            remVideoIds = LoadVideo(HomeMadeRemedies, view3);
            expVideoIds = LoadVideo(ExpVideos, view4);
            medVideoIds = LoadVideo(Meditation, view5);


        }
        catch (Exception ex)
        {
            ErrorHandling.ErrorDialog(ex.getMessage().toString(),this);
        }
        }

        public String[] LoadVideo (String purl,WebView view){

            String result="";

            JsonAsyncTask getRequest = new JsonAsyncTask();
            try {
                result = getRequest.execute(purl).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            result= result.replace("[","");
            result= result.replace("\"","");

            String[] IDArray = result.split(",");

            if(IDArray.length > 0){
                view.setVisibility(View.VISIBLE);
                PlayVideos(view,IDArray,0);
            }
            else {
                view.setVisibility(View.GONE);
            }
            return IDArray;
        }


        public void PlayVideos(WebView view, String[] videoId,int order) {
            try {
            /*final VideoView view1 =(VideoView) findViewById(R.id.videoView);

            MediaController mediaController= new MediaController(this);
            mediaController.setAnchorView(view1);

            Uri uri = Uri.parse("https://www.youtube.com/watch?v="+videoId[order].toString());
            view1.setMediaController(mediaController);
            view1.setVideoURI(uri);
            view1.start();*/


                view.getSettings().setJavaScriptEnabled(true);
                view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                view.getSettings().setLoadWithOverviewMode(true);
                view.getSettings().setUseWideViewPort(true);
                view.getSettings().setBuiltInZoomControls(true);
                view.loadUrl("https://www.youtube.com/embed/" + videoId[order].toString() + "?autoplay=1&vq=small");
                view.setWebChromeClient(new WebChromeClient());

            } catch (Exception ex) {
                ErrorHandling.ErrorDialog(ex.getMessage().toString(), this);
            }
        }


    @Override
    public void onLocationChanged(@NonNull Location location) {

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






