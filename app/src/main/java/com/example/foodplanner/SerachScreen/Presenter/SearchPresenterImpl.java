package com.example.foodplanner.SerachScreen.Presenter;

import com.example.foodplanner.SerachScreen.View.ISearchFragment;
import com.example.foodplanner.model.HomeRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class    SearchPresenterImpl implements  SearchPresenter {

    private HomeRepository homeRepository;
    private static final String TAG = "SearchPresenterImpl";

    private ISearchFragment view;

    public SearchPresenterImpl( ISearchFragment view,HomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }


    @Override
    public void searchForAMeal(String type, String name){
        homeRepository.searchForAMeal(name,type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(parentMeal-> view.setData(parentMeal.getMeals()));
    }

}
