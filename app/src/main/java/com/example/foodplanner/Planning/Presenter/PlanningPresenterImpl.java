package com.example.foodplanner.Planning.Presenter;

import com.example.foodplanner.Planning.View.IPlanningFragment;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Meal;

public class PlanningPresenterImpl implements PlanningPresenter {
    private IPlanningFragment view;
    private HomeRepository repository = null;
    private static final String TAG = "PlanningPresenterImpl";

    public PlanningPresenterImpl(IPlanningFragment view, HomeRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getPlanningItem(){
        view.setData(repository.getAllSavedMeal());
    }

    @Override
    public void removeItem(Meal meal) {
        repository.deleteMeal(meal);
    }
}
