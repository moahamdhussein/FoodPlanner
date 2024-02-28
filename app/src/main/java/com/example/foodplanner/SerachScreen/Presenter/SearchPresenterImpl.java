package com.example.foodplanner.SerachScreen.Presenter;

import static android.provider.MediaStore.Video.VideoColumns.CATEGORY;

import android.util.Log;
import android.widget.SearchView;

import androidx.annotation.NonNull;

import com.example.foodplanner.R;
import com.example.foodplanner.SerachScreen.View.ISearchFragment;
import com.example.foodplanner.SerachScreen.View.SearchFragment;
import com.example.foodplanner.model.HomeRepository;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class    SearchPresenterImpl implements  SearchPresenter {

    private HomeRepository homeRepository;
    private static final String TAG = "SearchPresenterImpl";
    private ISearchFragment view;
    String type;
    public SearchPresenterImpl( ISearchFragment view,HomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }


    @Override
    public void startSearch(SearchView searchView, ChipGroup chipGroup, Chip chipArea, Chip chipIngredients, Chip chipCategory, Chip chipMeal) {
        type = "s";
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Throwable {
                final String[] name = new String[1];
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        name[0] = newText;
                        emitter.onNext(name[0]);
                        return true;
                    }

                });
                chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
                    @Override
                    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                        if (chipArea.isChecked()) {
                            type = SearchFragment.AREA;
                            chipArea.setChipBackgroundColorResource(R.color.checkedChips);
                        } else {
                            chipArea.setChipBackgroundColorResource(R.color.white);
                        }
                        if (chipCategory.isChecked()) {
                            type = SearchFragment.CATEGORY;

                            chipCategory.setChipBackgroundColorResource(R.color.checkedChips);
                        } else {
                            chipCategory.setChipBackgroundColorResource(R.color.white);
                        }
                        if (chipIngredients.isChecked()) {
                            type = SearchFragment.INGREDIENTS;
                            chipIngredients.setChipBackgroundColorResource(R.color.checkedChips);
                        } else {
                            chipIngredients.setChipBackgroundColorResource(R.color.white);
                        }
                        if (chipMeal.isChecked()) {
                            type = SearchFragment.MEAL_NAME;
                            chipMeal.setChipBackgroundColorResource(R.color.checkedChips);
                        }else{
                            chipMeal.setChipBackgroundColorResource(R.color.white);
                        }
                        if (name[0]!=null && !name[0].isEmpty()){
                            emitter.onNext(name[0]);
                        }

                        Log.i(TAG, "onCheckedChanged:     " + type);
                    }
                });
            }
        });

        observable.debounce(500, TimeUnit.MILLISECONDS).subscribe(name -> {

            if (name != null && !name.isEmpty()) {
               searchForAMeal(type, name);
            }
        });
    }
    @Override
    public void searchForAMeal(String type, String name){
        homeRepository.searchForAMeal(name,type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(parentMeal-> view.setData(parentMeal.getMeals()));
    }

}
