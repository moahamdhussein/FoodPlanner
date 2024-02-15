package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParentIngredients {

   @SerializedName("meals")
   public ArrayList<Ingredients> ingredients;

   public ArrayList<Ingredients> getIngredients() {
      return ingredients;
   }

   public void setIngredients(ArrayList<Ingredients> ingredients) {
      this.ingredients = ingredients;
   }
}
