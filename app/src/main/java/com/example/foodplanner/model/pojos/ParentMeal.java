package com.example.foodplanner.model.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ParentMeal{
   @SerializedName("meals")
   public ArrayList<Meal> meals;

   public ArrayList<Meal> getMeals() {
      return meals;
   }

   public void setMeals(ArrayList<Meal> meals) {
      this.meals = meals;
   }
}

