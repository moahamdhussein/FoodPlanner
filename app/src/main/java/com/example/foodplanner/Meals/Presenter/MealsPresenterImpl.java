package com.example.foodplanner.Meals.Presenter;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.Ingredients;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.Meals.View.IMealsFragment;
import com.example.foodplanner.Network.NetworkCallback;

import java.util.List;

public class MealsPresenterImpl implements NetworkCallback {

   private IMealsFragment view;
   private HomeRepository homeRepository;

   public MealsPresenterImpl(IMealsFragment view, HomeRepository homeRepository) {
      this.view = view;
      this.homeRepository = homeRepository;
   }

   public void getMeals(String query,String type){
      homeRepository.getMeals(this,query,type);
   }
   @Override
   public void onSuccessResults(List<Category> categories) {

   }

   @Override
   public void onSuccessResultsRandomMeal(Meal meals) {

   }

   @Override
   public void onFailureResult(String msg) {

   }

   @Override
   public void onSuccessResultsMealS(List<Meal> meals) {
         view.setMealsList(meals);
   }

   @Override
   public void onSuccessResultsIngredients(List<Ingredients> ingredients) {

   }

   @Override
   public void onSuccessAreaResult(List<Area> areas) {

   }
}
