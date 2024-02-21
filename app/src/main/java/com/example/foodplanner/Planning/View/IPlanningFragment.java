package com.example.foodplanner.Planning.View;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface IPlanningFragment {

  public void setData(List<Meal> meals);

  public void filterListMeal(String date);

}
