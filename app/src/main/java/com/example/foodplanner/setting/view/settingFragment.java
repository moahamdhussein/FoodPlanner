package com.example.foodplanner.setting.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;

import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSourceImpl;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.setting.presenter.SettingPresenter;
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

public class settingFragment extends Fragment implements ISettingFragment {

    Button btnBackup;
    TextView tvUserName;

    SettingPresenter presenter;

    private static final String TAG = "settingFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnBackup = view.findViewById(R.id.btn_backup);
        tvUserName = view.findViewById(R.id.tv_user_name);
        presenter = new SettingPresenter(this,HomeRepository
                .getInstance(CategoryRemoteDataSourceImpl.getInstance(getContext()), RandomRemoteDataSourceImpl.getInstance(getContext()),
                        IngredientsRemoteDataSourceImpl.getInstance(getContext()), MealLocalDataSourceImpl.getInstance(getContext())));
        btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.backup();
            }
        });
    }
    @Override
    public  void setData(String username){
        tvUserName.setText(username);
    }
}

