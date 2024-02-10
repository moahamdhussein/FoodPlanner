package com.example.foodplanner.MainScreen.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

private List<Category> categories;

private final Context context;
private static final String TAG="HomeAdapter";

    public HomeAdapter(Context context,List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: ");
        return vh;
    }
    public void setList(List<Category> categories) {
        this.categories = categories;
    }
    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(categories.get(holder.getAdapterPosition()).getStrCategory());
        Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                .into(holder.imageView);
//        Picasso.get().load(categories.get(holder.getAdapterPosition()).getStrCategoryThumb()).into(holder.imageView);
        holder.tvDescription.setText(categories.get(holder.getAdapterPosition()).getStrCategoryDescription());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvTitle,tvDescription;
        private View layout;


        public Button btnSave;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            tvTitle =layout.findViewById(R.id.tv_title);
            imageView = layout.findViewById(R.id.iv_category);
            tvDescription = layout.findViewById(R.id.tv_description);
            Log.i(TAG, "ViewHolder: ");
        }
    }
}
