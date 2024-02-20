package com.example.foodplanner.model.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParentArea {

   @SerializedName("meals")

   List<Area> countries;

   public List<Area> getCountries() {
      return countries;
   }

   public void setCountries(List<Area> countries) {
      this.countries = countries;
   }
}
