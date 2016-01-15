package com.snapsoft.khain.photo_app.utils;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by khain on 09/01/2016.
 */
public class Alert {
    public static void showAlert(Context context,String text){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text);
        builder.setPositiveButton("OK",null);
        builder.create().show();
    }
}
