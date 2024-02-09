package com.example.foodplanner.Network;




import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.Meal;

import java.util.List;

public interface NetworkCallback {
   public void onSuccessResults(List<Category> categories);

   public void onSuccessResultsRandomMeal(List<Meal> meals);
   public void onFailureResult(String msg);
}
