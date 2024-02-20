package com.example.foodplanner.FavoriteList.Presenter;

import com.example.foodplanner.model.pojos.Meal;

public interface IFavouritePresenter {
    void getLocalData();

    void removeItem(Meal meal);
}
