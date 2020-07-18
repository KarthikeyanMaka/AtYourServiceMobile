package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import org.json.JSONException;

public class HelpLine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_line);
        try {

        String JsonData ="[{\"helpline_number\":\"044-29510500\",\"state_or_UT\":\"Tamil Nadu\",\"helpline_email\":\"ncov2019@gov.in\",\"globalhelpline_number\":\"+91-11-23978046\",\"source\":\"https://www.mohfw.gov.in/pdf/coronvavirushelplinenumber.pdf\",\"toll_free\":\"1075\"}]";

       // GridView objGrid = (GridView) findViewById(R.id.gvhelp);


      //  GridBinder.BindHelplineGrid(this,JsonData,objGrid);
        }
//        catch (JSONException e) {
//            ErrorHandling.ErrorDialog(e.getMessage().toString(),this);
//        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage().toString(),this);
        }


    }
}