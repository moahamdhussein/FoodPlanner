package com.example.foodplanner.SerachScreen.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Meal> meals;

    private Context context;

    private OnItemClick listener;

   public SearchAdapter( Context context,List<Meal> meals,OnItemClick listener) {
      this.meals = meals;
      this.context = context;
      this.listener = listener;
   }

   @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.search_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void setList(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    private static final String TAG = "SearchAdapter";
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {

        holder.tvTitle.setText(meals.get(holder.getAdapterPosition()).getStrMeal());
        holder.tvCategory.setText(meals.get(holder.getAdapterPosition()).getStrCategory());
        holder.tvArea.setText(meals.get(holder.getAdapterPosition()).getStrArea());
        Glide.with(context).load(meals.get(holder.getAdapterPosition()).getStrMealThumb()).into(holder.ivMeal);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,meals.get(holder.getAdapterPosition()).getStrMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (meals!=null){
            return meals.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMeal;
        TextView tvTitle, tvCategory, tvArea;

        View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ivMeal = itemView.findViewById(R.id.iv_meal);
            tvArea = itemView.findViewById(R.id.tv_meal_area);
            tvTitle = itemView.findViewById(R.id.tv_meal_title);
            tvCategory = itemView.findViewById(R.id.tv_meal_category);

        }
    }
}
