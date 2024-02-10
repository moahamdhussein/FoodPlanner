package com.example.foodplanner.MainScreen.presenter;


import com.example.foodplanner.MainScreen.View.IHome;
import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.HomeRepository;
import com.example.foodplanner.MainScreen.model.Meal;
import com.example.foodplanner.Network.NetworkCallback;

import java.util.List;

public class HomePresenter implements NetworkCallback, IHomePresenter {

   private static final String TAG = "HomePresenter";

   private IHome view;
   private HomeRepository homeRepository;




   public HomePresenter(IHome view, HomeRepository repository ) {
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
   public void onSuccessResultsRandomMeal(List<Meal> meals) {
      view.setRandomMeal(meals.get(0));
   }

   @Override
   public void onFailureResult(String msg) {
      view.showErrorMessage(msg);
   }

  @Override
   public void getRandomMeal() {
      homeRepository.getRandomMean(this);
   }
}
