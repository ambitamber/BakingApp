package com.example.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Parcelable {

    @SerializedName("mId")
    @Expose
    private int id;
    @SerializedName("mTitle")
    @Expose
    private String title;
    @SerializedName("mServings")
    @Expose
    private String servings;
    @SerializedName("mSteps")
    @Expose
    private String steps;
    @SerializedName("mStepsList")
    @Expose
    private List<Steps> stepsList;
    @SerializedName("mIngredientsList")
    @Expose
    private List<Ingredients> ingredientsList;

    public Recipe (String title,String servings,String steps){
        this.title = title;
        this.servings = servings;
        this.steps = steps;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        title = in.readString();
        servings = in.readString();
        steps = in.readString();
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

    //For StepsList data
    public List<Steps> getStepsList() {
        return stepsList;
    }
    public void setStepsList(List<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    //For Ingredients data
    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }
    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(servings);
        dest.writeString(steps);
    }
}
