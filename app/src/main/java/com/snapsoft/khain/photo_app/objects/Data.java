package com.snapsoft.khain.photo_app.objects;

/**
 * Created by khain on 09/01/2016.
 */
public class Data {
    int photoId;
    String photoUrl;
    String thumbnailUrl;
    String title;
    String description;
    int width;
    int height;
    int downloadCount;
    long createdTime;
    long modifiedTime;

    public Data(int photoId, String photoUrl, String thumbnailUrl, String title, String description, int width, int height, int downloadCount, long createdTime, long modifiedTime) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.width = width;
        this.height = height;
        this.downloadCount = downloadCount;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;

    }

    public int getPhotoId() {
        return photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public Data() {
    }

    public void setPhotoId(int photoId) {

        this.photoId = photoId;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

}
