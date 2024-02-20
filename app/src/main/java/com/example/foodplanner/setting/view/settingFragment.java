package com.example.foodplanner.setting.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;

import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.setting.presenter.SettingPresenter;

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
                .getInstance( RandomRemoteDataSourceImpl.getInstance(getContext()), MealLocalDataSourceImpl.getInstance(getContext())));
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

