package com.example.bakingapp.model;

import java.io.Serializable;

public class Recipe implements Serializable {

    private String title;
    private String servings;

    public Recipe (String title,String servings){
        this.title = title;
        this.servings = servings;
    }

    //For title data
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    //For serving data
    public void setServings(String servings) {
        this.servings = servings;
    }
    public String getServings() {
        return servings;
    }
}
