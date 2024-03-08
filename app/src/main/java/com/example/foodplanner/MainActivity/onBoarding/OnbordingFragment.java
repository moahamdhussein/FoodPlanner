package com.example.foodplanner.MainActivity.onBoarding;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodplanner.R;


public class OnbordingFragment extends Fragment {
    private ViewPager mSLideViewPager;
    private LinearLayout mDotLayout;
    private Button backbtn, nextbtn, skipbtn;
    private TextView[] dots;
    private OnBoardingAdapter adapter;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onbording, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backbtn = view.findViewById(R.id.backbtn);
        nextbtn = view.findViewById(R.id.nextbtn);
        skipbtn = view.findViewById(R.id.skipButton);
        preferences = requireActivity().getSharedPreferences("setting",MODE_PRIVATE);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) > 0){

                    mSLideViewPager.setCurrentItem(getitem(-1),true);

                }

            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 3)
                    mSLideViewPager.setCurrentItem(getitem(1),true);
                else {
                    editor = preferences.edit();
                    editor.putBoolean("OnBoardingDone",true);
                    editor.apply();
                    Navigation.findNavController(v).navigate(OnbordingFragmentDirections.actionOnbordingFragmentToLoginFragment());

                }

            }
        });
        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editor = preferences.edit();
                editor.putBoolean("OnBoardingDone",true);
                editor.apply();
                Navigation.findNavController(v).navigate(OnbordingFragmentDirections.actionOnbordingFragmentToLoginFragment());


            }
        });
        mSLideViewPager = (ViewPager) view.findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) view.findViewById(R.id.indicator_layout);

        adapter = new OnBoardingAdapter(getContext());

        mSLideViewPager.setAdapter(adapter);

        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);

    }

    public void setUpindicator(int position){

        dots = new TextView[4];
        mDotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(getContext());
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,requireActivity().getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active,requireActivity().getApplicationContext().getTheme()));

    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0){
                backbtn.setVisibility(View.VISIBLE);
            }else {
                backbtn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return mSLideViewPager.getCurrentItem() + i;
    }
}