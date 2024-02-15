package com.example.foodplanner.MainScreen.View;

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
import com.example.foodplanner.model.Ingredients;
import com.example.foodplanner.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{
    private List<Ingredients> ingredients;

    private final Context context;

    private IHome view;

    public IngredientAdapter( Context context,List<Ingredients> ingredients,IHome view) {
        this.ingredients = ingredients;
        this.context = context;
        this.view = view;
    }

    private static final String TAG = "IngredientAdapter";
    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.ingredients_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: ");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(ingredients.get(holder.getAdapterPosition()).getStrIngredient());
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/"+ingredients.get(holder.getAdapterPosition()).getStrIngredient()+".png")
                .into(holder.imageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.ingredientClick(v,ingredients.get(holder.getAdapterPosition()).getStrIngredient());
            }
        });

    }
    public void setList(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View layout;

        private ImageView imageView;
        private TextView tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            tvTitle = layout.findViewById(R.id.tv_title_ingredient);
            imageView = layout.findViewById(R.id.iv_ingredient);
        }
    }
}
