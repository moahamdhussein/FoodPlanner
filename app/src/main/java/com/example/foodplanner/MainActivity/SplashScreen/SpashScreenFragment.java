package com.example.foodplanner.MainActivity.SplashScreen;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.MainScreen.MainScreen;
import com.example.foodplanner.R;



public class SpashScreenFragment extends Fragment {

    Animation topAnim, bottomAnim;
    LottieAnimationView lottieAnimationView;
    TextView tvTitle, tvSlogan;
    Handler handler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topAnim = AnimationUtils.loadAnimation(getContext(), R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_animation);

        lottieAnimationView = view.findViewById(R.id.animationView);

        tvTitle = view.findViewById(R.id.tv_title);
        tvSlogan = view.findViewById(R.id.tv_slogan);
        lottieAnimationView.setAnimation(topAnim);

        tvTitle.setAnimation(bottomAnim);
        tvSlogan.setAnimation(bottomAnim);
        handler = new Handler();
        NavController controller = Navigation.findNavController(view);
        SharedPreferences preferences = getActivity().getSharedPreferences("setting",MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("loggedInUser",false);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn){
                    startActivity(new Intent(getContext(), MainScreen.class));
                    getActivity().finish();
                }else {
                    controller.navigate(SpashScreenFragmentDirections.actionSpashScreenFragmentToLoginFragment());
                }

            }
        },3200);
    }
}