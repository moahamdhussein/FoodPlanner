package com.example.foodplanner.MealDetails.View;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Constant;
import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.MealDetails.Presenter.MealDetailsPresenterImpl;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSourceImpl;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Calendar;

public class MealDetailsFragment extends Fragment implements IMealDetailsFragment {

    private TextView tvTitle, tvCategory,tvArea,tvInstructions;
    private YouTubePlayerView playerView;
    private ImageView ivMeal;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MealDetailsAdapter adapter;
    private static final String TAG = "MealDetailsFragment";
    private MealDetailsPresenterImpl presenter;
    private boolean Colored = false ;
    private FloatingActionButton fabAddToFav,addToPlanning;

    private Meal meal;
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
        recyclerView = view.findViewById(R.id.rv_ingredients);
        tvInstructions = view.findViewById(R.id.tv_instructions);
        playerView = view.findViewById(R.id.wv_video_link);
        fabAddToFav = view.findViewById(R.id.fab_add_to_fav);
        addToPlanning = view.findViewById(R.id.fab_add_to_plan);

        getLifecycle().addObserver(playerView);


        layoutManager =new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MealDetailsAdapter(new ArrayList<>(),new ArrayList<>(),getContext());
        recyclerView.setAdapter(adapter);
        String mealName = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName();
        Log.i(TAG, "onViewCreated: "+Constant.CountryCode.get("American"));
        presenter = new MealDetailsPresenterImpl(this, HomeRepository.getInstance(CategoryRemoteDataSourceImpl.getInstance(getContext()),
                RandomRemoteDataSourceImpl.getInstance(getContext()),
                IngredientsRemoteDataSourceImpl.getInstance(getContext()), MealLocalDataSourceImpl.getInstance(getContext())));
        Log.i(TAG, "onViewCreated: "+MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName());


        presenter.getMealDetails(MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName());

        fabAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Colored ) {
                    Colored = !Colored;
                    fabAddToFav.setIcon(R.drawable.favorite_outline);

                    presenter.removeFromFavourite(meal);
                }else {
                    Colored = !Colored;
                    fabAddToFav.setIcon(R.drawable.favorite__colored);
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                    String date=year+"-"+(month+1)+"-"+dayOfMonth;
                    meal.setPlanDate(date);
                    meal.setDbType("Favourite");
                    presenter.addToFav(meal);
                }

            }
        });

        addToPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                Log.i(TAG, "onClick: "+year+"-"+(month+1)+"-"+dayOfMonth);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Do something with the selected date
                                String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                Toast.makeText(getContext(), "Meal Saved with Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                                meal.setPlanDate(selectedDate);
                                meal.setDbType("Plan");
                                presenter.addToFav(meal);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });
    }
    @Override
    public void getMealDetails(Meal meal){
        this.meal = meal;
        tvTitle.setText(meal.getStrMeal());
        tvCategory.setText(meal.getStrCategory());
        tvArea.setText(meal.getStrArea());
        Glide.with(getContext()).load(meal.getStrMealThumb())
                .into(ivMeal);
        Log.i(TAG, "onViewCreated: "+Constant.CountryCode.get(meal.getStrArea()));
        tvInstructions.setText(meal.getStrInstructions());
        playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {


            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                Log.i(TAG, "onReady: "+meal.youtubeId());
                String videoId = meal.youtubeId();
                Log.i(TAG, "onReady: "+videoId);
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
        adapter.setList(meal.getIngredient(),meal.getMeasurement());
        adapter.notifyDataSetChanged();

    }


}