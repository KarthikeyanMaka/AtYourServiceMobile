package appcommon;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.example.atyourservice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GridBinder {
    public static void BindHospitalGrid (Context c, String JsonData, GridView gv) throws  JSONException{
        try{
            JSONArray ja = new JSONArray(JsonData);
            JSONObject jo;


            ArrayList<CovidHospitallist> jsonlist = new ArrayList<>();

            jsonlist.clear();

            for (int i=0;i<ja.length();i++) {
                CovidHospitallist objhoslist = new CovidHospitallist();

                jo = ja.getJSONObject(i);
                String hosname= "";
                String hoscity = "";
                String hosowner = "";
                String hosbeds = "";

                hosname =jo.getString("name");
                hoscity =jo.getString("city");
                hosowner =jo.getString("ownership");
                hosbeds =jo.getString("hospitalBeds");



                objhoslist.name=hosname;
                objhoslist.city=hoscity;
                objhoslist.ownership=hosowner;
                objhoslist.hospitalBeds=hosbeds;

                jsonlist.add(objhoslist);

            }

            RecoveredCountsModel.HospitalGrid objhosgrid = new RecoveredCountsModel.HospitalGrid(c,jsonlist);

            gv.setAdapter(objhosgrid);


        }
        catch (Exception ex)
        {throw ex;}

    }
    public static void BindCovidLabGrid(Context c, String jsonData, GridView gv) throws JSONException {
        try{

            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;


            ArrayList<CovidLabModel> jsonlist = new ArrayList<>();

            jsonlist.clear();

            for (int i=0;i<ja.length();i++) {
                CovidLabModel objlabmodel = new CovidLabModel();

                jo = ja.getJSONObject(i);
                String hosname= "";
                String hoscity = "";
                String hosowner = "";
                String hosbeds = "";

                objlabmodel.category =jo.getString("category");
                objlabmodel.city =jo.getString("city");
                objlabmodel.contact =jo.getString("contact");
                objlabmodel.phonenumber =jo.getString("phonenumber");
                objlabmodel.descriptionandorserviceprovided =jo.getString("descriptionandorserviceprovided");
                objlabmodel.nameoftheorganisation =jo.getString("nameoftheorganisation");
                objlabmodel.state =jo.getString("state");

                jsonlist.add(objlabmodel);

            }

            LabGridAdapter objlabGrid = new LabGridAdapter(c,jsonlist);

            gv.setAdapter(objlabGrid);

        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public static void BindCovidDashGrid(Context c, String jsonData, GridView gv) throws JSONException {
        try{
            JSONArray ja= new JSONArray(jsonData);
            JSONObject jo;

            RecoveredCountsModel objCounts = new RecoveredCountsModel();

            ArrayList<RecoveredCountsModel> jsonlist= new ArrayList<RecoveredCountsModel>();
            jsonlist.clear();

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                objCounts.active = jo.getString("active");
                objCounts.confirmed = jo.getString("confirmed");
                objCounts.deceased = jo.getString("deceased");
                objCounts.recovered = jo.getString("recovered");
                objCounts.dtRecordedDate = jo.getString("dtRecordedDate");
                objCounts.notes = jo.getString("notes");
                objCounts.districtName = jo.getString("districtName");
                objCounts.stateName = jo.getString("stateName");

                jsonlist.add(objCounts);

            }


            CovidDashboadAdapter objHelpView = new CovidDashboadAdapter(c,jsonlist);

            gv.setAdapter(objHelpView);



        }
        catch (Exception ex)
        {throw ex;}


    }

    public static void BindHelplineGrid (Context c, String jsonData, GridView gv,boolean isCentral) throws JSONException {
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;
            StateHelplinelist objhelplst = new StateHelplinelist();
            
            ArrayList<StateHelplinelist> jsonlist= new ArrayList<StateHelplinelist>();


            jsonlist.clear();

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String helpline;
                String helpemail;
                String tollfree;

                if(isCentral) {
                    helpline = jo.getString("globalhelpline_number");
                    helpemail = "";
                    tollfree = jo.getString("toll_free");
                }
                else{
                    helpline = jo.getString("helpline_number");
                    helpemail = jo.getString("helpline_email");
                    tollfree = jo.getString("toll_free");
                }

                objhelplst.helpline_email= helpemail;
                objhelplst.helpline_number=helpline;
                objhelplst.toll_free=tollfree;

                jsonlist.add(objhelplst);

            }


            GridAdapter objHelpView = new GridAdapter(c,jsonlist,isCentral);


            gv.setAdapter(objHelpView);


            }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public static void BindCityDropDown (Context c, String jsonData, Spinner gv) throws JSONException{

        try{

            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            List<String> list = new ArrayList<String>();
            list.add("");
            for(int count=0; count<ja.length(); count++){
                list.add(ja.getString(count));
            }

            String[] cityArray = list.toArray(new String[list.size()]);
            ArrayAdapter<String> jsonlist= new ArrayAdapter<String>(c, R.layout.support_simple_spinner_dropdown_item,cityArray);

            gv.setAdapter(jsonlist);


        }catch (Exception ex)
        {throw ex;}

    }

    public static void BindStateDropDown (Context c, String jsonData, Spinner gv) throws JSONException {
        try {

            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            List<String> stateCodelist =new ArrayList<String>();
            stateCodelist.add("");

            for (int i=0;i<ja.length();i++) {
                jo = ja.getJSONObject(i);

                stateCodelist.add(jo.getString("stateCode") +","+jo.getString("stateName"));

            }

            String[] stateArray = stateCodelist.toArray(new String[stateCodelist.size()]);

            ArrayAdapter<String> jsonlist= new ArrayAdapter<String>(c,R.layout.support_simple_spinner_dropdown_item,stateArray);

            gv.setAdapter(jsonlist);

        } catch (Exception ex) {
            throw ex;
        }
    }
}
