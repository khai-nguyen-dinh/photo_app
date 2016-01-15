package com.snapsoft.khain.photo_app.server;

/**
 * Created by khain on 09/01/2016.
 */
public class UrlServer {
    public static String url(String text){
        String BASE_URL = "http://mobilewalls-snapsofts.rhcloud.com/mobilewalls/api/";
        return BASE_URL += text;
    }
}
