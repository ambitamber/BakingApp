package com.example.bakingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredients {

    @SerializedName("mQuantity")
    @Expose
    private String quantity;
    @SerializedName("mMeasure")
    @Expose
    private String measure;
    @SerializedName("mIngredient")
    @Expose
    private String ingredient;

    public Ingredients(String quantity,String measure,String ingredient){
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    //For quantity data
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    //For measure data
    public String getMeasure() {
        return measure;
    }
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    //For ingredient data
    public String getIngredient() {
        return ingredient;
    }
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
