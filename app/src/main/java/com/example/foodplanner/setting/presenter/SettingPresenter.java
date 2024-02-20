package com.example.foodplanner.setting.presenter;


import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.setting.view.ISettingFragment;

public class SettingPresenter implements ISettingPresenter {

    private HomeRepository homeRepository;
    private ISettingFragment view;

    public SettingPresenter (ISettingFragment view,HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
        this.view = view;
    }

    private static final String TAG = "SettingPresenter";

    @Override
    public void backup() {

        homeRepository.backup();
    }
}
