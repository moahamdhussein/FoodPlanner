package com.example.foodplanner.MealDetails.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MealDetails.Presenter.MealDetailsPresenterImpl;
import com.example.foodplanner.MainScreen.model.HomeRepository;
import com.example.foodplanner.MainScreen.model.Meal;
import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSourceImpl;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;

public class MealDetailsFragment extends Fragment implements IMealDetailsFragment {

    TextView tvTitle, tvCategory,tvArea;
    ImageView ivMeal;
    private static final String TAG = "MealDetailsFragment";
    MealDetailsPresenterImpl presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tv_title);
        tvCategory = view.findViewById(R.id.tv_category);
        tvArea = view.findViewById(R.id.tv_area);
        ivMeal = view.findViewById(R.id.iv_meal);
        presenter = new MealDetailsPresenterImpl(this, HomeRepository.getInstance(CategoryRemoteDataSourceImpl.getInstance(),
                RandomRemoteDataSourceImpl.getInstance(),
                IngredientsRemoteDataSourceImpl.getInstance()));
        Log.i(TAG, "onViewCreated: "+MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName());
        presenter.getMealDetails(MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName());

    }
    @Override
    public void getMealDetails(Meal meal){
        tvTitle.setText(meal.getStrMeal());
        tvCategory.setText(meal.getStrCategory());
        tvArea.setText(meal.getStrArea());
        Glide.with(getContext()).load(meal.getStrMealThumb())
                .into(ivMeal);
    }


}