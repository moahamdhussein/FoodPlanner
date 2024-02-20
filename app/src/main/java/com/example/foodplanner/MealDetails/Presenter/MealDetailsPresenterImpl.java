package com.example.foodplanner.MealDetails.Presenter;

import com.example.foodplanner.MealDetails.View.IMealDetailsFragment;
import com.example.foodplanner.model.IHomeRepository;
import com.example.foodplanner.model.pojos.Area;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Ingredients;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.Network.NetworkCallback;

import java.util.List;

public class MealDetailsPresenterImpl implements NetworkCallback, IMealDetailsPresenter {

    private IMealDetailsFragment view;

    private IHomeRepository homeRepository;

    private static final String TAG = "MealDetailsPresenterImp";

    public MealDetailsPresenterImpl(IMealDetailsFragment view, IHomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }

    @Override
    public void onSuccessResults(List<Category> categories) {

    }

    @Override
    public void getMealDetails(String name) {
        homeRepository.getMealWithName(this, name);
    }

    @Override
    public void onSuccessResultsRandomMeal(Meal meals) {
        meals.setIngredientAndMeasurement();

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

    @Override
    public void onSuccessAreaResult(List<Area> areas) {

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
