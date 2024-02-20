package com.example.foodplanner.Meals.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.R;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHoder> {
    private List<Meal> meals;
    private Context context;

    public MealsAdapter( Context context,List<Meal> meals) {
        this.meals = meals;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.meal_item, parent, false);
        ViewHoder viewHoder = new ViewHoder(v);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.tvMealTitle.setText(meals.get(holder.getAdapterPosition()).getStrMeal());
        Glide.with(context).load(meals.get(holder.getAdapterPosition()).getStrMealThumb())
                .into(holder.ivMealImage);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(
                        MealsFragmentDirections.actionMealsFragmentToInfoFragment(
                                meals.get(holder.getAdapterPosition()).getStrMeal()
                        )
                );
            }
        });

    }
    public void setList(List<Meal> meals){
        this.meals = meals;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (meals!=null){
            return meals.size();
        }
        return 0;
    }

    class ViewHoder extends RecyclerView.ViewHolder {
        ImageView ivMealImage;
        TextView tvMealTitle;
        View layout;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ivMealImage = itemView.findViewById(R.id.iv_meal);
            tvMealTitle = itemView.findViewById(R.id.tv_meal_title);
        }

    }
}
