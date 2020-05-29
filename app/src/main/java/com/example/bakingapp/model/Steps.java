package com.example.bakingapp.model;

public class Steps {

    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Steps (String shortDescription,String description, String videoURL, String thumbnailURL){
        this.shortDescription = shortDescription;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.videoURL = videoURL;
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
