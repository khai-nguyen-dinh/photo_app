package com.snapsoft.khain.photo_app.objects;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by khain on 09/01/2016.
 */
public class ObjectData {
    public static Data getData(String json) {
        Data temp = new Data();
        try {
            JSONObject jsonObject = new JSONObject(json);
            temp.setPhotoId(jsonObject.getInt("photoId"));
            temp.setPhotoUrl(jsonObject.getString("photoUrl"));
            temp.setThumbnailUrl(jsonObject.getString("thumbnailUrl"));
            temp.setTitle(jsonObject.getString("title"));
            temp.setDescription(jsonObject.getString("description"));
            temp.setWidth(jsonObject.getInt("width"));
            temp.setHeight(jsonObject.getInt("height"));
            temp.setDownloadCount(jsonObject.getInt("downloadCount"));
            temp.setCreatedTime(jsonObject.optLong("createdTime"));
            temp.setModifiedTime(jsonObject.getLong("modifiedTime"));
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
