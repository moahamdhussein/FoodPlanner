package com.example.foodplanner.SerachScreen.Presenter;

import android.widget.SearchView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public interface SearchPresenter {
    void searchForAMeal(String type, String name);
    void startSearch(SearchView searchView, ChipGroup chipGroup,Chip chipArea,Chip chipIngredients,Chip chipCategory,Chip chipMeal);
}
