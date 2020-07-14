package com.example.atyourservice;

import android.net.Uri;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.app.Activity;
import android.graphics.Bitmap;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    private String [] dietVideoIds= new String[5];
    private String [] immVideoIds= new String[5];
    private String [] remVideoIds= new String[5];
    private String [] expVideoIds= new String[5];
    private String [] medVideoIds= new String[5];
    private int lstDietId=0;
    private int lstImmId=0;
    private int lstRemId=0;
    private int lstExpId=0;
    private int lstMedId=0;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the spinner from the xml.
        final Spinner dropdown = findViewById(R.id.spinner1);



        String[] items = new String[]{"தமிழ்", "हिन्दी", "English","ಕನ್ನಡ","తెలుగు","മലയാളം"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);




        final ScrollView scrollView=(ScrollView) findViewById(R.id.Scroll1);
        scrollView.setVerticalScrollbarPosition(ScrollView.SCROLLBAR_POSITION_RIGHT);
        scrollView.setVerticalScrollBarEnabled(true);

        CreateLinkPlay("தமிழ்");

       /*
        mWebViewClient = new myWebViewClient();
        view1.setWebViewClient(mWebViewClient);

        mWebChromeClient = new myWebChromeClient();
        view1.setWebChromeClient(mWebChromeClient);
*/


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                CreateLinkPlay(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        }
        public void PlayNexDietVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView1);

            if (lstDietId > (dietVideoIds.length - 1))
                lstDietId = 0;
            else
                lstDietId = lstDietId + 1;

            PlayVideos(view1,dietVideoIds,lstDietId);
        }
        public void PlayNexImmVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView2);

            if (lstImmId > (immVideoIds.length - 1))
                lstImmId = 0;
            else
                lstImmId = lstImmId + 1;

            PlayVideos(view1,immVideoIds,lstImmId);
        }
        public void PlayNexRemVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView3);

            if (lstRemId> (remVideoIds.length - 1))
                lstRemId = 0;
            else
                lstRemId= lstRemId + 1;

            PlayVideos(view1,remVideoIds,lstRemId);
        }
        public void PlayNexExpVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView4);

            if (lstExpId> (expVideoIds.length - 1))
                lstExpId = 0;
            else
                lstExpId= lstExpId + 1;

            PlayVideos(view1,expVideoIds,lstExpId);
        }
        public void PlayNexMedVideo(View view)
        {
            final WebView view1=(WebView) findViewById(R.id.mWebView5);

            if (lstMedId> (medVideoIds.length - 1))
                lstMedId = 0;
            else
                lstMedId= lstMedId + 1;

            PlayVideos(view1,medVideoIds,lstMedId);
        }


    public void CreateLinkPlay(String language){
            String urllng = "Tamil";

            switch (language) {
                case "தமிழ்":
                    urllng = "Tamil";
                    break;
                case "English":
                    urllng = "English";
                    break;
                case "తెలుగు":
                    urllng = "Telugu";
                    break;
                case "ಕನ್ನಡ":
                    urllng = "Kannada";
                    break;
                case "മലയാളം":
                    urllng = "Malayalam";
                    break;
                case "हिन्दी":
                    urllng = "Hindi";
                    break;

            }

            final WebView view1=(WebView) findViewById(R.id.mWebView1);
            final WebView view2=(WebView) findViewById(R.id.mWebView2);
            final WebView view3=(WebView) findViewById(R.id.mWebView3);
            final WebView view4=(WebView) findViewById(R.id.mWebView4);
            final WebView view5=(WebView) findViewById(R.id.mWebView5);


            String ImmBoosURL="https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllYoutubeID/ImmunityBooster/"+urllng+"/Rating/5";
            String DietPlan="https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllYoutubeID/DietPlan/"+urllng+"/Rating/5";
            String HomeMadeRemedies="https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllYoutubeID/HomeMadeRemedies/"+urllng+"/Rating/5";
            String ExpVideos="https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllYoutubeID/Covid%20Experience/"+urllng+"/Views/5";
            String Meditation="https://atyoursupport20200712092520.azurewebsites.net/api/Data/GetAllYoutubeID/meditation/"+urllng+"/Views/5";

            dietVideoIds=LoadVideo(DietPlan,view1);
            immVideoIds=LoadVideo(ImmBoosURL,view2);
            remVideoIds=LoadVideo(HomeMadeRemedies,view3);
            expVideoIds=LoadVideo(ExpVideos,view4);
            medVideoIds=LoadVideo(Meditation,view5);

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


        public void PlayVideos(WebView view, String[] videoId,int order){
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
            view.loadUrl("https://www.youtube.com/embed/"+videoId[order].toString()+"?autoplay=1&vq=small");
            view.setWebChromeClient(new WebChromeClient());

        }


}






