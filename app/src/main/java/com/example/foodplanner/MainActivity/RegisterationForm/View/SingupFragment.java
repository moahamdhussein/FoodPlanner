package com.example.foodplanner.MainActivity.RegisterationForm.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodplanner.MainActivity.RegisterationForm.Presenter.RegisterPresenter;
import com.example.foodplanner.MainScreen.MainScreen;
import com.example.foodplanner.R;



public class SingupFragment extends Fragment implements View.OnClickListener , ISingUp {
    Button btnSignUp;
    EditText etEmail,etPassword;

    RegisterPresenter presenter;

    private static final String TAG = "SingupFragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSignUp = view.findViewById(R.id.btn_signup);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        presenter =new RegisterPresenter(getActivity(),this);

        btnSignUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        presenter.createNewAccount(etEmail.getText().toString(),etPassword.getText().toString());
    }

    @Override
    public void onSuccessComplete() {
        Intent intent = new Intent(getContext(), MainScreen.class);
        startActivity(intent);
        getActivity().finish();
    }
}