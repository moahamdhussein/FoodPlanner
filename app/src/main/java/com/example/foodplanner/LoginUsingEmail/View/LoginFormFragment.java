package com.example.foodplanner.LoginUsingEmail.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodplanner.LoginUsingEmail.Presenter.LoginFormPresenter;
import com.example.foodplanner.MainScreen.MainScreen;
import com.example.foodplanner.R;
import com.example.foodplanner.RegisterationForm.Presenter.RegisterPresenter;

public class LoginFormFragment extends Fragment implements View.OnClickListener, ILoginForm{
    Button btnLogin;
    EditText etEmail,etPassword;

    LoginFormPresenter presenter;
    private static final String TAG = "LoginFormFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogin =view.findViewById(R.id.btn_lgoin);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);

        presenter = new LoginFormPresenter(getActivity(),this);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        presenter.login(etEmail.getText().toString(),etPassword.getText().toString());
    }

    @Override
    public void onSuccessCompleteLogin() {
        Intent intent = new Intent(getContext(), MainScreen.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onErrorMessage() {

    }
}