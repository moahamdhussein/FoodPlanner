package com.example.foodplanner.Planning.View;

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
import com.example.foodplanner.model.Meal;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PlanningAdapter extends RecyclerView.Adapter<PlanningAdapter.ViewHolder> {

   List<Meal> meals;
   Context context;

   OnRemoveClick listener;

   public PlanningAdapter( Context context,List<Meal> meals,OnRemoveClick listener) {
      this.meals = meals;
      this.context = context;
      this.listener = listener;
   }

   @NonNull
   @Override
   public PlanningAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View v = inflater.inflate(R.layout.planing_item , parent,false);
      ViewHolder vh = new ViewHolder(v);
      return vh;
   }

   public void setList(List<Meal> meals){
      this.meals = meals;
      notifyDataSetChanged();
   }

   @Override
   public void onBindViewHolder(@NonNull PlanningAdapter.ViewHolder holder, int position) {
      Glide.with(context).load(meals.get(holder.getAdapterPosition()).getStrMealThumb())
              .into(holder.ivHead);
      holder.tvTitle.setText(meals.get(holder.getAdapterPosition()).getStrMeal());
      holder.tvCategory.setText(meals.get(holder.getAdapterPosition()).getStrCategory());
      holder.fabRemove.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            listener.onRemoveClick(meals.get(holder.getAdapterPosition()));
         }
      });
   }

   @Override
   public int getItemCount() {
      return meals.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder{

      private FloatingActionButton fabRemove;
      private TextView tvTitle,tvCategory;
      private ImageView ivHead;

      private View layout;
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         layout = itemView;
         fabRemove = itemView.findViewById(R.id.fab_delete_item);
         tvCategory = itemView.findViewById(R.id.text_category);
         tvTitle = itemView.findViewById(R.id.text_title);
         ivHead = itemView.findViewById(R.id.iv_head);

      }
   }
}
