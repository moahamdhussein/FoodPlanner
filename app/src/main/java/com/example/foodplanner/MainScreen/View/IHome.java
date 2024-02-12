package com.example.foodplanner.MainScreen.View;


import android.view.View;

import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.Ingredients;
import com.example.foodplanner.MainScreen.model.Meal;

import java.util.List;

public interface IHome {

 public void showData(List<Category> products);

 public void showErrorMessage(String error);

 public void setRandomMeal(Meal meal);

 public void setIngredientData(List<Ingredients> ingredients);

 public  void onDailyMailClick(Meal meal);

 public void categoryClick(View view,String name);
 public void ingredientClick(View view,String name);

}
