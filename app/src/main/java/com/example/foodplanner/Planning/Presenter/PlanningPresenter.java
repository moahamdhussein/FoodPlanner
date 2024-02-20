package com.example.foodplanner.Planning.Presenter;

import com.example.foodplanner.model.pojos.Meal;

public interface PlanningPresenter {
    public void getPlanningItem();

    public void removeItem(Meal meal);
}
