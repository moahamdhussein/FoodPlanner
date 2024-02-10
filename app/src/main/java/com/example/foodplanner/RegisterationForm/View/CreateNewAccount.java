package com.example.foodplanner.RegisterationForm.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodplanner.R;

public class CreateNewAccount extends AppCompatActivity {

    Button btnSignUp;
    EditText etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        btnSignUp = findViewById(R.id.btn_signup);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

    }
}