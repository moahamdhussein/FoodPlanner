package com.example.foodplanner.SerachScreen.Presenter;

import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.SerachScreen.View.ISearchFragment;
import com.example.foodplanner.model.pojos.Area;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Ingredients;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public class SearchPresenterImpl implements NetworkCallback, SearchPresenter {

    private HomeRepository homeRepository;
    private static final String TAG = "SearchPresenterImpl";

    private ISearchFragment view;

    public SearchPresenterImpl( ISearchFragment view,HomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }

    @Override
    public void onSuccessResults(List<Category> categories) {

    }

    @Override
    public void searchForAMeal(String type, String name){
        homeRepository.searchForAMeal(this ,name,type);
    }

    @Override
    public void onSuccessResultsRandomMeal(Meal meals) {

    }

    @Override
    public void onFailureResult(String msg) {
    }

    @Override
    public void onSuccessResultsMealS(List<Meal> meals) {
        view.setData(meals);
    }

    @Override
    public void onSuccessResultsIngredients(List<Ingredients> ingredients) {

    }

    @Override
    public void onSuccessAreaResult(List<Area> areas) {

    }
}
