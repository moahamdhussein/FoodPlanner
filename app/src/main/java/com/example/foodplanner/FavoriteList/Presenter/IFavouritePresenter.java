package com.example.foodplanner.FavoriteList.Presenter;

import com.example.foodplanner.model.Meal;

interface IFavouritePresenter {
    void getLocalData();

    void removeItem(Meal meal);
}
