package com.snapsoft.khain.photo_app.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by khain on 09/01/2016.
 */
public class commonUtils {
    public static ProgressDialog showProgressDialog(Context context,String text){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(text);
        progressDialog.show();
        return progressDialog;
    }
}
