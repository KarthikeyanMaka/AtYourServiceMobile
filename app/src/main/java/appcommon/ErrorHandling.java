package appcommon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public  class ErrorHandling {
    public static void ErrorDialog(String Messsage , final Context appContent ) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(appContent);
        dialog.setMessage(Messsage);
        dialog.setTitle("Dialog Box");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(appContent,"Yes is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(appContent,"cancel is clicked",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
    public static void AlertMessage(String Messsage , final Context appContent) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(appContent);
        dialog.setMessage(Messsage);
        dialog.setTitle("Dialog Box");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(appContent,"Yes is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(appContent,"cancel is clicked",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}

