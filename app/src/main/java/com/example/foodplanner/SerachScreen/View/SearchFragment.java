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
import com.example.foodplanner.Network.Random.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.SerachScreen.Presenter.SearchPresenter;
import com.example.foodplanner.SerachScreen.Presenter.SearchPresenterImpl;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Meal;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;


public class SearchFragment extends Fragment implements ISearchFragment, OnItemClick {

    private static final String TAG = "SearchFragment";
    private SearchView searchView;
    private SearchPresenter presenter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SearchAdapter searchAdapter;
    private ChipGroup chipGroup;
    private Chip chipArea, chipIngredients, chipCategory, chipMeal;
    public static final String AREA = "a";
    public static final String CATEGORY = "c";
    public static final String INGREDIENTS = "i";
    public static final String MEAL_NAME = "s";


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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search_bar);
        recyclerView = view.findViewById(R.id.rv_search_result);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        searchAdapter = new SearchAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchAdapter);
        chipGroup = view.findViewById(R.id.chipGroup);
        chipArea = view.findViewById(R.id.chip_area);
        chipCategory = view.findViewById(R.id.chip_category);
        chipIngredients = view.findViewById(R.id.chip_ingredients);
        chipMeal = view.findViewById(R.id.chip_meal);
        presenter = new SearchPresenterImpl(this, HomeRepository
                .getInstance(RemoteDataSourceImpl.getInstance(getContext()), MealLocalDataSourceImpl.getInstance(getContext())));

        presenter.startSearch(searchView, chipGroup, chipArea, chipIngredients, chipCategory, chipMeal);
    }

    @Override
    public void setData(List<Meal> meals) {
        searchAdapter.setList(meals);
        searchAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(View view, String mealName) {
        Navigation.findNavController(view).navigate(SearchFragmentDirections.actionSearchFragmentToInfoFragment(mealName));
    }
}