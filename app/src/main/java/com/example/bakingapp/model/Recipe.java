package com.example.bakingapp.model;

import java.io.Serializable;

public class Recipe implements Serializable {

    private String title;
    private String servings;
    private String steps;

    public Recipe (String title,String servings,String steps){
        this.title = title;
        this.servings = servings;
        this.steps = steps;
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

    //For steps data
    public void setSteps(String steps) {
        this.steps = steps;
    }
    public String getSteps() {
        return steps;
    }
}
