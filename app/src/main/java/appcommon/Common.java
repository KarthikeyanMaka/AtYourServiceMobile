package appcommon;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.widget.Button;

import androidx.core.app.NotificationCompat;

import com.example.atyourservice.R;

public class Common {

    public static final String API_SERVER ="https://atyoursupport20200811122207.azurewebsites.net/api/Data/";
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
    public static void sendNotification(Context mcontext) {

        //Get an instance of NotificationManager//
        try {

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(mcontext, "1")
                            .setSmallIcon(R.drawable.smartserveicon)
                            .setContentTitle(mcontext.getString(R.string.app_NotTitle))
                            .setContentText(mcontext.getString(R.string.app_Notmsg));

            // Gets an instance of the NotificationManager service//

            NotificationManager mNotificationManager =

                    (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);

            //NotificationManager.notify()
            mBuilder.setPriority(Notification.PRIORITY_MAX);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                String channelid ="001";
                NotificationChannel channel = new NotificationChannel(channelid,"Channel human Readable title",NotificationManager.IMPORTANCE_HIGH);

                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(channelid);


            }
            mNotificationManager.notify(0, mBuilder.build());
        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage(),mcontext);
        }
    }
}
