package com.example.foodplanner.FavoriteList.Presenter;

import com.example.foodplanner.FavoriteList.View.IFavouriteFragment;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Meal;

public class FavouritePresenterImpl implements IFavouritePresenter {

    private IFavouriteFragment view;

    private HomeRepository repository = null;

    private static final String TAG = "FavouritePresenterImpl";
    public FavouritePresenterImpl(IFavouriteFragment view, HomeRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getLocalData(){
        view.setData(repository.getStoredMeals());
    }
    @Override
    public void removeItem(Meal meal){
        repository.deleteMeal(meal);
    }

}
