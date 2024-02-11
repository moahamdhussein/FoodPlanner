package com.example.foodplanner.Network;






import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.Ingredients;
import com.example.foodplanner.MainScreen.model.Meal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

import java.util.List;

public interface NetworkCallback {
   public void onSuccessResults(List<Category> categories);

   public void onSuccessResultsRandomMeal(Meal meals);
   public void onFailureResult(String msg);
   public  void onSuccessResultsIngredients(List<Ingredients> ingredients);


}
