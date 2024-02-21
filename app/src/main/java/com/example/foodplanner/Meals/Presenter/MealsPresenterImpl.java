package com.example.foodplanner.Meals.Presenter;

import com.example.foodplanner.model.IHomeRepository;
import com.example.foodplanner.Meals.View.IMealsFragment;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsPresenterImpl implements MealsPresenter {

   private IMealsFragment view;
   private IHomeRepository homeRepository;

   public MealsPresenterImpl(IMealsFragment view, IHomeRepository homeRepository) {
      this.view = view;
      this.homeRepository = homeRepository;
   }

   @Override
   public void getMeals(String query, String type){
      homeRepository.getMeals(query,type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
              .subscribe(parentMeal-> view.setMealsList(parentMeal.getMeals()));
   }





}
