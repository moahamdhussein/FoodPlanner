package com.example.foodplanner.MainScreen.View;


import android.view.View;

import com.example.foodplanner.model.pojos.Area;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Ingredients;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface IHome {

 void showCategoryData(List<Category> products);

 void showErrorMessage(String error);

 void setRandomMeal(Meal meal);

 void setIngredientData(List<Ingredients> ingredients);


 void categoryClick(View view,String name);
 void ingredientClick(View view,String name);

 void setAreaList(List<Area> areas);

  void areaClick(View view , String name);

}
