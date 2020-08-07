package appcommon;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;

public class LocationFinder{
    private static DecimalFormat df2 = new DecimalFormat("#.#");

    public static String getLocationLang(Context currentScreen) throws JSONException {
        GpsTracker gpsTracker = new GpsTracker(currentScreen);
        String Locale = "en";
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            LangLocModel objlocModel ;

            String lat= df2.format(latitude);
            String longit= df2.format(longitude);

            String locationURL =Common.API_SERVER+"GetStateCitybyLoc/"+lat+"/"+longit;

            String result= ServerRequest(locationURL);

            objlocModel=GetLangLocData(result);

            Locale=GetLocale(objlocModel.language);

        }else{
            gpsTracker.showSettingsAlert();
        }
        return Locale;
    }
    public static LangLocModel GetCityState(Context currentScreen) throws JSONException {
        GpsTracker gpsTracker = new GpsTracker(currentScreen);
        String Locale = "en";
        LangLocModel objlocModel = null;
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();


            String lat= df2.format(latitude);
            String longit= df2.format(longitude);


            String locationURL =Common.API_SERVER+"GetStateCitybyLoc/"+String.valueOf(latitude)+"/"+String.valueOf(longitude);

           // ErrorHandling.ErrorDialog(String.valueOf(latitude)+String.valueOf(longitude)+"test:"+lat+longit,currentScreen);

            String result= ServerRequest(locationURL);

            objlocModel=GetLangLocData(result);



        }else{
            gpsTracker.showSettingsAlert();
        }
        return objlocModel;
    }

    public static String ServerRequest(String URL)
    {
        String result="";

        JsonAsyncTask getRequest = new JsonAsyncTask();
        try {
            result = getRequest.execute(URL).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static String GetLocale(String Language)
    {
        String locale ="en";

        try {
            switch (Language) {
                case "Tamil":
                    locale = "ta";
                    break;
                case "English":
                    locale = "en";
                    break;
                case "Telugu":
                    locale = "tel";
                    break;
                case "Kannada":
                    locale = "kan";
                    break;
                case "Malayalam":
                    locale = "mal";
                    break;
                case "Hindi":
                    locale = "hi";
                    break;
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return locale;
    }

    public static LangLocModel GetLangLocData(String jsonResult) throws JSONException {

        jsonResult = "["+jsonResult+"]";
        JSONArray ja = new JSONArray(jsonResult);
        JSONObject jo;

        LangLocModel objlangloc = new LangLocModel();

        for (int i=0;i<ja.length();i++) {
            jo = ja.getJSONObject(i);
            objlangloc.city=jo.getString("city");
            objlangloc.statecode=jo.getString("statecode");
            objlangloc.language=jo.getString("language");
        }

        return  objlangloc;
    }
    public static RecoveredCountsModel GetRecoveredData(String City, String State,Context currentScreen) throws JSONException {

        String Url =Common.API_SERVER+"GetRecoveredCountDist/"+State+"/"+City;

        return GetRecoveredCounts(ServerRequest(Url),currentScreen);
    }

    public static RecoveredCountsModel GetRecoveredCounts(String jsonResult,Context currentScreen) throws JSONException {

        JSONArray ja = new JSONArray(jsonResult);
        JSONObject jo;



        RecoveredCountsModel objRecovered = new RecoveredCountsModel();

        for (int i=0;i<ja.length();i++) {
            jo = ja.getJSONObject(i);
            objRecovered.active=jo.getString("active");
            objRecovered.deceased=jo.getString("deceased");
            objRecovered.confirmed=jo.getString("confirmed");
            objRecovered.recovered=jo.getString("recovered");
            objRecovered.dtRecordedDate=jo.getString("dtRecordedDate");
            objRecovered.stateName=jo.getString("stateName");
            objRecovered.districtName=jo.getString("districtName");
            objRecovered.notes=jo.getString("notes");
        }


        return  objRecovered;
    }
    public static boolean isNetworkStatusAvailable(Context context){
        try{

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager !=null){
                NetworkInfo netInfros = connectivityManager.getActiveNetworkInfo();
                if(netInfros !=null)
                    if(netInfros.isConnected())
                        return true;

            }
            return false;

        }
        catch (Exception ex){
            throw ex;
        }

    }

}