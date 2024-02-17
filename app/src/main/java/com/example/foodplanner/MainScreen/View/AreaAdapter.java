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
import com.example.foodplanner.Constant;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Area;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {

   List<Area> areas;
   Context context;

   IHome listener;

   private static final String TAG = "AreaAdapter";

   public AreaAdapter(Context context,List<Area> areas,IHome listener) {
      this.areas = areas;
      this.context = context;
      this.listener = listener;
   }

   @NonNull
   @Override
   public AreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View v = inflater.inflate(R.layout.area_item,parent,false);
      ViewHolder vh = new ViewHolder(v);
      return vh;
   }

   @Override
   public void onBindViewHolder(@NonNull AreaAdapter.ViewHolder holder, int position) {
      Log.i(TAG, "onBindViewHolder: "+areas.toString());

      holder.tvArea.setText(areas.get(holder.getAdapterPosition()).getStrArea());
      String countryCode = Constant.CountryCode.get(areas.get(holder.getAdapterPosition()).getStrArea());
      Log.i(TAG, "onBindViewHolder: "+countryCode);
      Glide.with(context).load("https://flagsapi.com/"+countryCode+"/shiny/64.png")
              .into(holder.ivArea);
      holder.layout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            listener.areaClick(v,areas.get(holder.getAdapterPosition()).getStrArea());
         }
      });
   }

   @Override
   public int getItemCount() {
      return areas.size();
   }

   public void setList(List<Area> areas) {

      this.areas =areas;
      notifyDataSetChanged();
   }

   public class ViewHolder extends RecyclerView.ViewHolder{
      ImageView ivArea;
      TextView tvArea;
      View layout;
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         layout=itemView;
         ivArea = itemView.findViewById(R.id.iv_area);
         tvArea= itemView.findViewById(R.id.tv_country_title);
      }
   }
}
