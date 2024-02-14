package com.example.foodplanner.MealDetails.Presenter;

import android.util.Log;

import com.example.foodplanner.MealDetails.View.IMealDetailsFragment;
import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.HomeRepository;
import com.example.foodplanner.MainScreen.model.Ingredients;
import com.example.foodplanner.MainScreen.model.Meal;
import com.example.foodplanner.Network.NetworkCallback;

import java.util.List;

public class MealDetailsPresenterImpl implements NetworkCallback ,IMealDetailsPresenter {

    private IMealDetailsFragment view;

    private HomeRepository homeRepository;

    private static final String TAG = "MealDetailsPresenterImp";

    public MealDetailsPresenterImpl(IMealDetailsFragment view, HomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }

    @Override
    public void onSuccessResults(List<Category> categories) {

    }

    @Override
    public void getMealDetails(String name){
        homeRepository.getMealWithName(this,name);
    }

    @Override
    public void onSuccessResultsRandomMeal(Meal meals) {
        meals.setIngredientAndMeasurement();

        Log.i(TAG, "onSuccessResultsRandomMeal: " +meals.getIngredient().toString());
        Log.i(TAG, "onSuccessResultsRandomMeal: " +meals.getMeasurement().toString());
        view.getMealDetails(meals);
    }

    @Override
    public void onFailureResult(String msg) {

    }

    @Override
    public void onSuccessResultsMealS(List<Meal> meals) {

    }

    @Override
    public void onSuccessResultsIngredients(List<Ingredients> ingredients) {

    }
}
