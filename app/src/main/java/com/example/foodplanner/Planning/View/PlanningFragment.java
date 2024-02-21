package com.example.foodplanner.Planning.View;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.Network.Random.RemoteDataSourceImpl;
import com.example.foodplanner.Planning.Presenter.PlanningPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Meal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;


public class PlanningFragment extends Fragment implements IPlanningFragment, OnRemoveClick {

    private static final String TAG = "PlanningFragment";
    private TextView textDate;
    private ImageView dropDownMenu;

    private GridLayoutManager gridLayoutManager;
    private RecyclerView planningRecyclerView;

    private PlanningPresenterImpl presenter;

    PlanningAdapter adapter;
    Calendar calendar;
    List<Meal> mealList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);

        presenter.getPlanningItem();

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<String> emitter) throws Throwable {
                dropDownMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                        // Do something with the selected date
                                        String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                        emitter.onNext(selectedDate);
                                    }
                                }, year, month, dayOfMonth);

                        datePickerDialog.show();
                    }
                });
            }
        });
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    textDate.setText(item);
                    filterListMeal(item);
                });


    }

    private void initialization(View view) {
        textDate = view.findViewById(R.id.tv_date);
        dropDownMenu = view.findViewById(R.id.iv_drop_down_date_menu);
        planningRecyclerView = view.findViewById(R.id.rv_planning_meal);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        planningRecyclerView.setLayoutManager(gridLayoutManager);
        adapter = new PlanningAdapter(getContext(), new ArrayList<>(), this);
        planningRecyclerView.setAdapter(adapter);
        presenter = new PlanningPresenterImpl(this, HomeRepository.getInstance(
                RemoteDataSourceImpl.getInstance(getContext())
                , MealLocalDataSourceImpl.getInstance(getContext())
        ));
        calendar = Calendar.getInstance();
        textDate.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void setData(List<Meal> meals) {
        this.mealList = meals;
        filterListMeal(textDate.getText().toString());

    }

    @Override
    public void filterListMeal(String date) {

        List<Meal> filteredList = mealList.stream().filter(meal -> meal.getPlanDate().equalsIgnoreCase(textDate.getText().toString())).collect(Collectors.toList());
        adapter.setList(filteredList);

    }

    @Override
    public void onRemoveClick(Meal meal) {
        presenter.removeItem(meal);
    }
}