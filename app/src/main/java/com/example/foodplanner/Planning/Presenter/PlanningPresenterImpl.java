package com.example.foodplanner.Planning.Presenter;

import com.example.foodplanner.Planning.View.IPlanningFragment;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Meal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

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
        repository.getAllSavedMeal().observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealList-> view.setData(mealList));
    }

    @Override
    public void removeItem(Meal meal) {
        repository.deleteMeal(meal);
    }
}
