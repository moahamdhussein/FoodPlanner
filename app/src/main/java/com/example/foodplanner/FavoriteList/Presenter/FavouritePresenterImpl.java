package com.example.foodplanner.FavoriteList.Presenter;

import com.example.foodplanner.FavoriteList.View.IFavouriteFragment;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.IHomeRepository;
import com.example.foodplanner.model.pojos.Meal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class FavouritePresenterImpl implements IFavouritePresenter {

    private IFavouriteFragment view;

    private IHomeRepository repository = null;

    private static final String TAG = "FavouritePresenterImpl";
    public FavouritePresenterImpl(IFavouriteFragment view, IHomeRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getLocalData(){
        repository.getStoredMeals().observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> view.setData(items));

    }
    @Override
    public void removeItem(Meal meal){
        repository.deleteMeal(meal);
    }

}
