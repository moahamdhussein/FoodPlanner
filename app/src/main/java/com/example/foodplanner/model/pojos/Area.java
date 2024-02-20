package com.example.foodplanner.model.pojos;

public class Area{
   private String strArea;

   public String getStrArea() {
      return strArea;
   }

   @Override
   public String toString() {
      return "Area{" +
              "strArea='" + strArea + '\'' +
              '}';
   }

   public void setStrArea(String strArea) {
      this.strArea = strArea;
   }
}
