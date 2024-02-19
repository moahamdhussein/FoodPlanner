package com.example.foodplanner.setting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;

import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSourceImpl;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.Meal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class settingFragment extends Fragment {


    List<Meal> meals;
    FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FirebaseApp.initializeApp(getContext());
        db = FirebaseFirestore.getInstance();
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    private static final String TAG = "settingFragment";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        meals = new ArrayList<>();

        HomeRepository repository = HomeRepository.getInstance(CategoryRemoteDataSourceImpl.getInstance(getContext()),
                RandomRemoteDataSourceImpl.getInstance(getContext()),
                IngredientsRemoteDataSourceImpl.getInstance(getContext()),
                MealLocalDataSourceImpl.getInstance(getContext()));

        repository.getAllSavedMeal().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> setList(meal),
                        throwable -> Log.i(TAG, "onViewCreated: " + throwable.getMessage()));


    }

    private void setList(List<Meal> meals) {

            Map<String, Object> userData = new HashMap<>();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            userData.put(user.getUid(),meals);
        Log.i(TAG, "setList: "+user.getUid());

            db.collection("users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    List<Meal> mealList =(List<Meal>) documentSnapshot.getData().get(user.getUid());
                    Log.i(TAG, "onSuccess: "+mealList.size());
                }
            });
            db.collection("users").document(user.getUid()).set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.i(TAG, "onSuccess: done");
                }
            });

//        }
        }
    }

