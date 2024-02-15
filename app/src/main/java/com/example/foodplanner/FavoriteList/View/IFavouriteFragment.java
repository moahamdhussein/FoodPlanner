package com.example.foodplanner.FavoriteList.View;

import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface IFavouriteFragment {
    public void setData(Flowable<List<Meal>> meals);
}
