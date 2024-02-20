package com.example.foodplanner.MainScreen.View;

import static androidx.core.content.ContextCompat.registerReceiver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;

import com.example.foodplanner.InterNetConnectivity;
import com.example.foodplanner.MainScreen.InternetConnection;
import com.example.foodplanner.MainScreen.presenter.IHomePresenter;
import com.example.foodplanner.model.pojos.Area;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Ingredients;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.MainScreen.presenter.HomePresenter;
import com.example.foodplanner.Network.Random.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements IHome , InterNetConnectivity {


    private RecyclerView categoryRecyclerView,ingredientRecyclerView,areaRecyclerView;
    private CategoryAdapter categoryAdapter;
    private IngredientAdapter ingredientAdapter;
    private AreaAdapter areaAdapter;

    private LinearLayoutManager categoryLayoutManager, ingredientLayoutManager,areaLayoutManager;
    private ImageView ivRandomMeal;
    private TextView tvRandomMealTitle, tv_RandomMealCategory ,tvCategoryTitle,tvIngredientTitle,tvTrendingFoodTitle;
    private View layoutRandomMeal;
    private LottieAnimationView loadingBar;

    private IHomePresenter presenter;
    private static final String TAG = "HomeFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Initialization(view);
        disableVisibility();
        InternetConnection internetConnection = new InternetConnection(this);
        getContext().registerReceiver(internetConnection, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        presenter = new HomePresenter(this,
                HomeRepository.getInstance(RemoteDataSourceImpl.getInstance(getContext()), MealLocalDataSourceImpl.getInstance(getContext())));
        presenter.getCategory();
        presenter.getRandomMeal();
        presenter.getAllIngredient();
        presenter.getAllContinues();
        layoutRandomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(HomeFragmentDirections
                        .actionHomeFragmentToInfoFragment(tvRandomMealTitle.getText().toString()));
            }
        });
    }

    private void Initialization(@NonNull View view) {
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        ivRandomMeal = view.findViewById(R.id.iv_random_meal);
        tvRandomMealTitle = view.findViewById(R.id.tv_random_meal_title);
        tv_RandomMealCategory = view.findViewById(R.id.tv_random_meal_category);
        ingredientRecyclerView = view.findViewById(R.id.my_ingredients_view);
        layoutRandomMeal = view.findViewById(R.id.cd_random_meal);
        loadingBar = view.findViewById(R.id.loading_bar);
        tvCategoryTitle = view.findViewById(R.id.tv_category_title);
        tvIngredientTitle = view.findViewById(R.id.tv_ingredients_title);
        tvTrendingFoodTitle = view.findViewById(R.id.tv_trending_food);
        areaRecyclerView = view.findViewById(R.id.my_area_view);

        areaLayoutManager = new LinearLayoutManager(getContext());
        areaLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryLayoutManager = new LinearLayoutManager(getContext());
        categoryLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        ingredientLayoutManager = new LinearLayoutManager(getContext());
        ingredientLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        areaRecyclerView.setLayoutManager(areaLayoutManager);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        ingredientRecyclerView.setLayoutManager(ingredientLayoutManager);
        areaAdapter = new AreaAdapter(getContext(),new ArrayList<>(),this);
        areaRecyclerView.setAdapter(areaAdapter);
        categoryAdapter = new CategoryAdapter(getContext(), new ArrayList<>(),this);
        categoryRecyclerView.setAdapter(categoryAdapter);
        ingredientAdapter = new IngredientAdapter(getContext() ,new ArrayList<>(),this);
        ingredientRecyclerView.setAdapter(ingredientAdapter);



    }

    private void disableVisibility(){
        tvCategoryTitle.setVisibility(View.GONE);
        tvIngredientTitle.setVisibility(View.GONE);
        tvTrendingFoodTitle.setVisibility(View.GONE);
        layoutRandomMeal.setVisibility(View.GONE);
        categoryRecyclerView.setVisibility(View.GONE);
        ingredientRecyclerView.setVisibility(View.GONE);
        areaRecyclerView.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);
        loadingBar.playAnimation();
    }
    private void enableVisibility(){
        tvCategoryTitle.setVisibility(View.VISIBLE);
        tvIngredientTitle.setVisibility(View.VISIBLE);
        tvTrendingFoodTitle.setVisibility(View.VISIBLE);
        layoutRandomMeal.setVisibility(View.VISIBLE);
        categoryRecyclerView.setVisibility(View.VISIBLE);
        ingredientRecyclerView.setVisibility(View.VISIBLE);
        areaRecyclerView.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.GONE);
    }



    @Override
    public void showData(List<Category> categories) {
        categoryAdapter.setList(categories);
        categoryAdapter.notifyDataSetChanged();
        enableVisibility();

    }

    @Override
    public void showErrorMessage(String error) {
//        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setRandomMeal(Meal meal) {
        tvRandomMealTitle.setText(meal.getStrMeal());
        tv_RandomMealCategory.setText("Category : " + meal.getStrCategory());
        Picasso.get().load(meal.getStrMealThumb()).into(ivRandomMeal);
        enableVisibility();
    }
    @Override
    public void setIngredientData(List<Ingredients> ingredients) {
        Collections.shuffle(ingredients);
        ingredientAdapter.setList(ingredients.subList(0,20));
        ingredientAdapter.notifyDataSetChanged();
        enableVisibility();

    }
    @Override
    public void setAreaList(List<Area> areas){
        areaAdapter.setList(areas);
        areaAdapter.notifyDataSetChanged();
        enableVisibility();
    };



    @Override
    public void categoryClick(View view,String name) {
        Navigation.findNavController(view).navigate(HomeFragmentDirections
                .actionHomeFragmentToMealsFragment("c",name));
    }
    @Override
    public void areaClick(View view , String name){
        Navigation.findNavController(view).navigate(HomeFragmentDirections
                .actionHomeFragmentToMealsFragment("a",name));
    }

    @Override
    public void ingredientClick(View view,String name) {
        Navigation.findNavController(view).navigate( HomeFragmentDirections.
                actionHomeFragmentToMealsFragment("i",name));
    }
    @Override
    public void onNetworkConnected() {
        disableVisibility();
        loadingBar.setAnimation(R.raw.animation8);
        loadingBar.playAnimation();
    }

    @Override
    public void onNetworkDisconnected() {
        disableVisibility();
        loadingBar.setAnimation(R.raw.not_internet);
        loadingBar.playAnimation();

    }
}