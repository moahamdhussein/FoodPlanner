package com.example.foodplanner.MainScreen.presenter;


import android.util.Log;

import com.example.foodplanner.MainScreen.View.IHome;
import com.example.foodplanner.model.IHomeRepository;
import com.example.foodplanner.model.pojos.Area;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Ingredients;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.Network.NetworkCallback;

import java.util.List;

public class HomePresenter implements NetworkCallback, IHomePresenter {

   private static final String TAG = "HomePresenter";

   private IHome view;
   private IHomeRepository homeRepository;


   private Meal meal;

   public Meal getMeal() {
      return meal;
   }

   public HomePresenter(IHome view, IHomeRepository repository ) {
      this.view = view;
      this.homeRepository = repository;

   }

   @Override
   public void getCategory(){
      homeRepository.getRemoteData(this);
   }



   @Override
   public void onSuccessResults(List<Category> categories) {
      view.showData(categories);
   }

   @Override
   public void onSuccessResultsRandomMeal(Meal meal) {
      this.meal = meal;
      view.setRandomMeal(meal);
   }

   @Override
   public void onFailureResult(String msg) {
      view.showErrorMessage(msg);
   }

   @Override
   public void onSuccessResultsMealS(List<Meal> meals) {

   }

   @Override
   public void onSuccessResultsIngredients(List<Ingredients> ingredients) {
      Log.i(TAG, "onSuccessResultsIngredients: "+ingredients.size());
      view.setIngredientData(ingredients);
   }

   @Override
   public void onSuccessAreaResult(List<Area> areas) {
      view.setAreaList(areas);
   }

   @Override
   public void getRandomMeal() {
      homeRepository.getRandomMean(this);
   }

   @Override
   public void getAllIngredient() {
      homeRepository.getAllIngredient(this);

   }
   @Override
    public void getAllContinues() {
      homeRepository.getAllContinues(this);
    }
}
