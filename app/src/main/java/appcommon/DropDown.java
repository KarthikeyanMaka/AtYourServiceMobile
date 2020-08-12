package appcommon;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;


public class DropDown {
    Spinner dropdown;

    public DropDown(Spinner pdropDown, Context screen, String locale)
    {
            this.dropdown = pdropDown;
            String[] items = new String[]{"", "தமிழ்", "हिन्दी", "English", "ಕನ್ನಡ", "తెలుగు", "മലയാളം","বাংলা"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(screen, android.R.layout.simple_spinner_dropdown_item, items);
            dropdown.setAdapter(adapter);

            if(locale!="")
            {
                dropdown.setSelection(ReturnLangPosition(locale));
            }

    }
    public DropDown(Spinner pdropDown, Context screen)
    {
        this.dropdown = pdropDown;
        String[] items = new String[]{"", "தமிழ்", "हिन्दी", "English", "ಕನ್ನಡ", "తెలుగు", "മലയാളം","বাংলা"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(screen, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);



    }
    public int ReturnLangPosition(String localname){
        try{
            int position = 0;
            switch (localname.toString()) {
                case "ta":
                    position = 1;
                    break;
                case "en":
                    position = 3;
                    break;
                case "tel":
                    position = 5;
                    break;
                case "kan":
                    position = 4;
                    break;
                case "mal":
                    position = 6;
                    break;
                case "hi":
                    position = 2;
                    break;
                case "bn":
                    position = 7;
                    break;
                default:
                    position=0;

            }
            return position;

        }
        catch (Exception ex){
            throw ex;
        }
    }
    public String ReturnSelectedLocale(String langString){
        String localeName = "en";

        switch (langString.toString()) {
            case "தமிழ்":
                localeName = "ta";
                break;
            case "English":
                localeName = "en";
                break;
            case "తెలుగు":
                localeName = "tel";
                break;
            case "ಕನ್ನಡ":
                localeName = "kan";
                break;
            case "മലയാളം":
                localeName = "mal";
                break;
            case "हिन्दी":
                localeName = "hi";
                break;
            case "বাংলা":
                localeName = "bn";
                break;

            }
        return localeName;
    }
}



