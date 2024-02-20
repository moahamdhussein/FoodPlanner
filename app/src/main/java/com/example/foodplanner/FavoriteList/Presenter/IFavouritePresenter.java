package com.example.foodplanner.FavoriteList.Presenter;

import com.example.foodplanner.model.pojos.Meal;

interface IFavouritePresenter {
    void getLocalData();

    void removeItem(Meal meal);
}
