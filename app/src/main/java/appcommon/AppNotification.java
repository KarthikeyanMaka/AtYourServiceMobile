package appcommon;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.atyourservice.HomeActivity;
import com.example.atyourservice.R;

public class AppNotification {
    public static void NotificationBuildShow(Context context){
        try{

            NotificationCompat.Builder builder= new NotificationCompat.Builder(context)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText("This is test notification");

            Intent notifcationIntent = new Intent(context, HomeActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context,0,notifcationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0,builder.build());


        }
        catch (Exception ex){
            throw ex;
        }

    }
}
