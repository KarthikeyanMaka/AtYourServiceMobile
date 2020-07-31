package appcommon;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;


public class DropDown {
    Spinner dropdown;

    public DropDown(Spinner pdropDown, Context screen)
    {
            this.dropdown = pdropDown;
            String[] items = new String[]{"", "தமிழ்", "हिन्दी", "English", "ಕನ್ನಡ", "తెలుగు", "മലയാളം"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(screen, android.R.layout.simple_spinner_dropdown_item, items);
            dropdown.setAdapter(adapter);
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

            }
        return localeName;
    }
}



