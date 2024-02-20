package com.example.foodplanner.MainScreen.View;


import android.view.View;

import com.example.foodplanner.model.pojos.Area;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Ingredients;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface IHome {

 public void showData(List<Category> products);

 public void showErrorMessage(String error);

 public void setRandomMeal(Meal meal);

 public void setIngredientData(List<Ingredients> ingredients);


 public void categoryClick(View view,String name);
 public void ingredientClick(View view,String name);

 public void setAreaList(List<Area> areas);

 public void areaClick(View view , String name);

}
