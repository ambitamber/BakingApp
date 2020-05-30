package com.example.bakingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Steps {
    @SerializedName("mId")
    @Expose
    private int id;
    @SerializedName("mShortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("mDescription")
    @Expose
    private String description;
    @SerializedName("mVideoURL")
    @Expose
    private String videoURL;
    @SerializedName("mThumbnailURL")
    @Expose
    private String thumbnailURL;

    public Steps (String shortDescription,String description, String videoURL, String thumbnailURL){
        this.shortDescription = shortDescription;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.videoURL = videoURL;
    }

    //For id data
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    //For getShortDescription data
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    //For description data
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //for thumbnailURL data
    public String getThumbnailURL() {
        return thumbnailURL;
    }
    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    //for videoURL data
    public String getVideoURL() {
        return videoURL;
    }
    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
