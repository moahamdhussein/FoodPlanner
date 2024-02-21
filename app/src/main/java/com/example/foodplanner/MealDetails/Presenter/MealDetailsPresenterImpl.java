package com.example.foodplanner.MealDetails.Presenter;

import android.util.Log;

import com.example.foodplanner.MealDetails.View.IMealDetailsFragment;
import com.example.foodplanner.model.IHomeRepository;
import com.example.foodplanner.model.pojos.Meal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImpl implements  IMealDetailsPresenter {

    private IMealDetailsFragment view;

    private IHomeRepository homeRepository;

    private static final String TAG = "MealDetailsPresenterImp";

    public MealDetailsPresenterImpl(IMealDetailsFragment view, IHomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }


    @Override
    public void getMealDetails(String name) {
        homeRepository.getMealWithName(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(parentMeal -> {
                Meal meal = parentMeal.getMeals().get(0);
                meal.setIngredientAndMeasurement();
                view.getMealDetails(meal);
                },
                        throwable -> Log.i(TAG, "getMealDetails: "));
    }


    @Override
    public void addToFav(Meal meal) {

        homeRepository.insertMeal(meal);
    }

    @Override
    public void removeFromFavourite(Meal meal) {
        homeRepository.deleteMeal(meal);
    }
}
