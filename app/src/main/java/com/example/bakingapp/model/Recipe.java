package com.example.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Recipe implements Parcelable {

    private int id;
    private String name;
    private String servings;
    private List<Steps> steps;
    private List<Ingredients> ingredients;

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    //For id data
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //For title data
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    //For serving data
    public void setServings(String servings) {
        this.servings = servings;
    }
    public String getServings() {
        return servings;
    }


    //For StepsList data
    public List<Steps> getSteps() {
        return steps;
    }
    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    //For Ingredients data
    public List<Ingredients> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(servings);
    }
}
