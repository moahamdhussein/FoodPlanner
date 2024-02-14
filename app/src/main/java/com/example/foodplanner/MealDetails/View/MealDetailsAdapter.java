package com.example.foodplanner.MealDetails.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;

import java.util.List;


public class MealDetailsAdapter extends RecyclerView.Adapter<MealDetailsAdapter.ViewHolder> {

   List<String> ingredients;
   List<String> measurements;

   Context context;

   public MealDetailsAdapter(List<String> ingredients, List<String> measurements, Context context) {
      this.ingredients = ingredients;
      this.measurements = measurements;
      this.context = context;
   }

   @NonNull
   @Override
   public MealDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View v = inflater.inflate(R.layout.meal_ingredient_items, parent, false);
      MealDetailsAdapter.ViewHolder vh = new MealDetailsAdapter.ViewHolder(v);
      return vh;
   }

   @Override
   public void onBindViewHolder(@NonNull MealDetailsAdapter.ViewHolder holder, int position) {
      holder.tvIngredient.setText(ingredients.get(holder.getAdapterPosition()));
      holder.tvMeasurement.setText(measurements.get(holder.getAdapterPosition()));

      Glide.with(context)
              .load("https://www.themealdb.com/images/ingredients/"+ingredients.get(holder.getAdapterPosition())+"-Small.png")
              .into(holder.ivIngredient);
   }

   @Override
   public int getItemCount() {
      return ingredients.size();
   }

   public void setList(List<String> ingredients,List<String> measurements) {
      this.ingredients = ingredients;
      this.measurements = measurements;
      notifyDataSetChanged();
   }


   public class ViewHolder extends RecyclerView.ViewHolder{

      ImageView ivIngredient;
      TextView tvMeasurement,tvIngredient;
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         ivIngredient = itemView.findViewById(R.id.iv_ingredient);
         tvIngredient = itemView.findViewById(R.id.tv_ingredient);
         tvMeasurement = itemView.findViewById(R.id.tv_measurement);

      }
   }
}
