package com.example.foodplanner.SerachScreen.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSourceImpl;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.SerachScreen.Presenter.SearchPresenterImpl;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.Meal;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;


public class SearchFragment extends Fragment implements ISearchFragment, OnItemClick {

    private static final String TAG = "SearchFragment";
    private SearchView searchView;

    private SearchPresenterImpl presenter;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private SearchAdapter searchAdapter;

    private ChipGroup chipGroup;

    private Chip chipArea, chipIngredients, chipCategory, chipMeal;

    private final String AREA = "a";
    private final String CATEGORY = "c";
    private final String INGREDIENTS = "i";

    private final String MEAL_NAME = "s";

    String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search_bar);
        recyclerView = view.findViewById(R.id.rv_search_result);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        searchAdapter = new SearchAdapter(getContext(), new ArrayList<>(),this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchAdapter);
        chipGroup = view.findViewById(R.id.chipGroup);
        chipArea = view.findViewById(R.id.chip_area);
        chipCategory = view.findViewById(R.id.chip_category);
        chipIngredients = view.findViewById(R.id.chip_ingredients);
        chipMeal = view.findViewById(R.id.chip_meal);
        type = "s";

        presenter = new SearchPresenterImpl(this, HomeRepository
                .getInstance(CategoryRemoteDataSourceImpl.getInstance(getContext()), RandomRemoteDataSourceImpl.getInstance(getContext()),
                        IngredientsRemoteDataSourceImpl.getInstance(getContext()), MealLocalDataSourceImpl.getInstance(getContext())));


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
                            type = AREA;
                            chipArea.setChipBackgroundColorResource(R.color.checkedChips);
                        } else {
                            chipArea.setChipBackgroundColorResource(R.color.white);
                        }
                        if (chipCategory.isChecked()) {
                            type = CATEGORY;

                            chipCategory.setChipBackgroundColorResource(R.color.checkedChips);
                        } else {
                            chipCategory.setChipBackgroundColorResource(R.color.white);
                        }
                        if (chipIngredients.isChecked()) {
                            type = INGREDIENTS;
                            chipIngredients.setChipBackgroundColorResource(R.color.checkedChips);
                        } else {
                            chipIngredients.setChipBackgroundColorResource(R.color.white);
                        }
                        if (chipMeal.isChecked()) {
                            type = MEAL_NAME;
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
                presenter.searchForAMeal(type, name);
            }
        });

    }

    @Override
    public void setData(List<Meal> meals) {

        searchAdapter.setList(meals);
        searchAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(View view, String mealName){
        Navigation.findNavController(view).navigate(SearchFragmentDirections.actionSearchFragmentToInfoFragment(mealName));
    }
}