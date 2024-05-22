package com.example.foodplanner.setting.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.InterNetConnectivity;
import com.example.foodplanner.MainScreen.InternetConnection;
import com.example.foodplanner.Network.Random.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.setting.presenter.ISettingPresenter;
import com.example.foodplanner.setting.presenter.SettingPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class settingFragment extends Fragment implements ISettingFragment , InterNetConnectivity {

    private Button btnBackup,btnLogout;
    private TextView tvUserName;
    private ISettingPresenter presenter;
    private SharedPreferences sharedPreferences;
    private boolean isConnected;

    private InternetConnection internetConnection;

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
        btnLogout = view.findViewById(R.id.btn_logout);

        tvUserName = view.findViewById(R.id.tv_user_name);
        isConnected = true;
        presenter = new SettingPresenter(this,HomeRepository
                .getInstance( RemoteDataSourceImpl.getInstance(getContext()), MealLocalDataSourceImpl.getInstance(getContext())));
        btnBackup.setOnClickListener(v -> {
            if (isConnected){
                presenter.backup();
                Snackbar.make(requireView(),"Backup Done Successfully",Snackbar.LENGTH_SHORT).show();
            }else {
                Snackbar.make(requireView(),"Failed to Backup open internet and try again",Snackbar.LENGTH_SHORT).show();

            }

        });

        internetConnection = new InternetConnection(this);
        requireActivity().registerReceiver(internetConnection, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        sharedPreferences = requireActivity().getSharedPreferences("setting", MODE_PRIVATE);
        boolean isGuest = sharedPreferences.getBoolean("isGuest",false);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        btnLogout.setOnClickListener(v -> {
            if (isConnected&&!isGuest) {
                MealLocalDataSourceImpl localDataSource =  MealLocalDataSourceImpl.getInstance(getContext());
                localDataSource.deleteAllData();
                FirebaseAuth.getInstance().signOut();
                editor.putBoolean("isGuest",false);
                editor.putBoolean("loggedInUser",false);
                editor.apply();
            }else if (!isConnected){
                editor.putBoolean("isGuest",false);
                editor.putBoolean("loggedInUser",true);
                editor.apply();
            }
            requireActivity().finish();
        });

    }
    @Override
    public  void setData(String username){
        tvUserName.setText(username);
    }

    @Override
    public void onNetworkConnected() {
        isConnected = true;
    }

    @Override
    public void onNetworkDisconnected() {
        isConnected = false;
    }
}

