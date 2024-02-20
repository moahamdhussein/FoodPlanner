package com.example.foodplanner.Network;






import com.example.foodplanner.model.pojos.Area;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Ingredients;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface NetworkCallback {
   public void onSuccessResults(List<Category> categories);

   public void onSuccessResultsRandomMeal(Meal meals);
   public void onFailureResult(String msg);

   public void onSuccessResultsMealS(List<Meal> meals);
   public  void onSuccessResultsIngredients(List<Ingredients> ingredients);

   public  void onSuccessAreaResult(List<Area> areas);


}
