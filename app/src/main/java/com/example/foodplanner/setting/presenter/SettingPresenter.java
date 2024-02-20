package com.example.foodplanner.setting.presenter;


import android.util.Log;

import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.setting.view.ISettingFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SettingPresenter implements ISettingPresenter {

    private HomeRepository homeRepository;
    private ISettingFragment view;

    public SettingPresenter (ISettingFragment view,HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
        this.view = view;
    }

    private static final String TAG = "SettingPresenter";

    public void backup() {

        homeRepository.backup();
    }
}
