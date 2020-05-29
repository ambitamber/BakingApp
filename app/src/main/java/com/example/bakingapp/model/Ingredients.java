package com.example.bakingapp.model;

public class Ingredients {

    private String quantity;
    private String measure;
    private String ingredient;

    public Ingredients(String quantity,String measure,String ingredient){
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    //For
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
