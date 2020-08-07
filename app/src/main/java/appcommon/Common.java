package appcommon;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

public class Common {

    public static final String API_SERVER ="https://atyoursupport20200712092520.azurewebsites.net/api/Data/";
    public static void setMenuColor(Button b1, Button b2, Button b3, Button b4, String curScreen, Context context){

      /*  b1.setBackgroundColor(Color.GRAY);
        b2.setBackgroundColor(Color.GRAY);
        b3.setBackgroundColor(Color.GRAY);
        b4.setBackgroundColor(Color.GRAY);*/

        int color = Color.TRANSPARENT;

        switch (curScreen){
            case "Home":
                b1.setBackgroundColor(color);
                break;
            case "Health":
                b2.setBackgroundColor(color);
                break;
            case "Emer":
                b3.setBackgroundColor(color);
                break;
            case "Dash":
                b4.setBackgroundColor(color);
                break;
        }

    }
    public static void SetInternalMenuColor(Button b1)
    {
        b1.setBackgroundColor(Color.rgb(200,213,220));
    }
}
