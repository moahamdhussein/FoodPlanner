package com.example.foodplanner.MealDetails.Presenter;

import com.example.foodplanner.model.pojos.Meal;

public interface IMealDetailsPresenter {
    void getMealDetails(String name);

    void addToFav(Meal meal);

    void removeFromFavourite(Meal meal);
}
