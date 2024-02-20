package com.example.foodplanner.Meals.Presenter;

import com.example.foodplanner.model.IHomeRepository;
import com.example.foodplanner.model.pojos.Area;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Ingredients;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.Meals.View.IMealsFragment;
import com.example.foodplanner.Network.NetworkCallback;

import java.util.List;

public class MealsPresenterImpl implements NetworkCallback, MealsPresenter {

   private IMealsFragment view;
   private IHomeRepository homeRepository;

   public MealsPresenterImpl(IMealsFragment view, IHomeRepository homeRepository) {
      this.view = view;
      this.homeRepository = homeRepository;
   }

   @Override
   public void getMeals(String query, String type){
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
