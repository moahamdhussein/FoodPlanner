package com.example.foodplanner.MainScreen.presenter;


import com.example.foodplanner.MainScreen.View.IHome;
import com.example.foodplanner.model.IHomeRepository;
import com.example.foodplanner.model.pojos.Meal;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter<T> implements IHomePresenter {

    private static final String TAG = "HomePresenter";

    private IHome view;
    private IHomeRepository homeRepository;


    private Meal meal;

    public Meal getMeal() {
        return meal;
    }

    public HomePresenter(IHome view, IHomeRepository repository) {
        this.view = view;
        this.homeRepository = repository;

    }

    @Override
    public void getCategory() {
        homeRepository.getRemoteData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> view.showCategoryData(categories.getCategories()),
                        throwable -> view.showErrorMessage(throwable.getMessage()));
    }


    @Override
    public void getRandomMeal() {
        homeRepository.getRandomMeal().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(parentMeal -> view.setRandomMeal(parentMeal.getMeals().get(0)),
                        throwable -> view.showErrorMessage(throwable.getMessage()));
    }

    @Override
    public void getAllIngredient() {
        homeRepository.getAllIngredient().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(parentIngredients -> view.setIngredientData(parentIngredients.getIngredients()),
                        throwable -> view.showErrorMessage(throwable.getMessage()));

    }

    @Override
    public void getAllContinues() {
        homeRepository.getAllContinues().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(parentArea -> view.setAreaList(parentArea.getCountries()),
                        throwable -> view.showErrorMessage(throwable.getMessage()));
    }
}
