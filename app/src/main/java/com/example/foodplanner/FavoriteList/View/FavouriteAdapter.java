package com.example.foodplanner.FavoriteList.View;

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
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

   Context context;
   List<Meal> meals;

   OnRemoveClick removeListener;

   OnFavouriteItemClick favouriteListener;

   private static final String TAG = "FavouriteAdapter";

   public FavouriteAdapter(Context context, List<Meal> meals,OnRemoveClick removeListener,OnFavouriteItemClick favouriteListener) {
      this.context = context;
      this.meals = meals;
      this.removeListener = removeListener;
      this.favouriteListener = favouriteListener;
   }


   @NonNull
   @Override
   public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View v = inflater.inflate(R.layout.favourite_item,parent,false);
      ViewHolder vh = new ViewHolder(v);
      Log.i(TAG, "onCreateViewHolder: ");
      return vh;
   }
   @Override
   public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {
      Glide.with(context).load(meals.get(holder.getAdapterPosition()).getStrMealThumb())
              .into(holder.imageHead);
      holder.tvTitle.setText(meals.get(holder.getAdapterPosition()).getStrMeal());
      holder.tvCategory.setText(meals.get(holder.getAdapterPosition()).getStrCategory());
      holder.fabDeleteItem.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            removeListener.onRemoveClick(meals.get(holder.getAdapterPosition()));
         }
      });
      holder.layout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            favouriteListener.onFavItemClick(v,meals.get(holder.getAdapterPosition()));
         }
      });
   }

   public void setList(List<Meal> meals){
//      Log.i(TAG, "setList: "+meals.toString());
      this.meals = meals;
      notifyDataSetChanged();
   }

   @Override
   public int getItemCount() {
      return meals.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder{
      ImageView imageHead;
      TextView tvTitle,tvCategory;

      FloatingActionButton fabDeleteItem;

      View layout;
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         Log.i(TAG, "ViewHolder: ");
         layout =itemView;
         imageHead = itemView.findViewById(R.id.iv_head);
         tvTitle = itemView.findViewById(R.id.text_title);
         tvCategory = itemView.findViewById(R.id.text_category);
         fabDeleteItem = itemView.findViewById(R.id.fab_delete_item);
      }
   }
}
