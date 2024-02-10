package com.example.foodplanner.MainScreen.View;


import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.Ingredients;
import com.example.foodplanner.MainScreen.model.Meal;

import java.util.List;

public interface IHome {

 public void showData(List<Category> products);

 public void showErrorMessage(String error);

 public void setRandomMeal(Meal meal);

 public void setIngredientData(List<Ingredients> ingredients);

}
