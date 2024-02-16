package com.example.foodplanner.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "favourite_meal")
public class Meal {


    @ColumnInfo(name = "id")
    private String idMeal;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String strMeal;

    @ColumnInfo(name = "category")
    private String strCategory;

    @ColumnInfo(name = "area")
    private String strArea;

    @ColumnInfo(name = "instructions")
    private String strInstructions;

    @ColumnInfo(name = "image url")
    private String strMealThumb;

//    @ColumnInfo(name = "image_bitmap")
//    byte[] imageInByte;

//    public byte[] getImageInByte() {
//        return imageInByte;
//    }
//
//    public void setImageInByte(byte[] imageInByte) {
//        this.imageInByte = imageInByte;
//    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    @ColumnInfo(name = "youtube link")
    private String strYoutube;

    @Ignore
    private String strIngredient1;
    @Ignore
    private String strIngredient2;
    @Ignore
    private String strIngredient3;
    @Ignore
    private String strIngredient4;
    @Ignore
    private String strIngredient5;
    @Ignore
    private String strIngredient6;
    @Ignore
    private String strIngredient7;
    @Ignore
    private String strIngredient8;
    @Ignore
    private String strIngredient9;
    @Ignore
    private String strIngredient10;
    @Ignore
    private String strIngredient11;
    @Ignore
    private String strIngredient12;
    @Ignore
    private String strIngredient13;
    @Ignore
    private String strIngredient14;
    @Ignore
    private String strIngredient15;
    @Ignore
    private String strIngredient16;
    @Ignore
    private String strIngredient17;
    @Ignore
    private String strIngredient18;
    @Ignore
    private String strIngredient19;
    @Ignore
    private String strIngredient20;
    @Ignore
    private String strMeasure1;
    @Ignore
    private String strMeasure2;
    @Ignore
    private String strMeasure3;
    @Ignore
    private String strMeasure4;
    @Ignore
    private String strMeasure5;
    @Ignore
    private String strMeasure6;
    @Ignore
    private String strMeasure7;
    @Ignore
    private String strMeasure8;
    @Ignore
    private String strMeasure9;
    @Ignore
    private String strMeasure10;
    @Ignore
    private String strMeasure11;
    @Ignore
    private String strMeasure12;
    @Ignore
    private String strMeasure13;
    @Ignore
    private String strMeasure14;
    @Ignore
    private String strMeasure15;
    @Ignore
    private String strMeasure16;
    @Ignore
    private String strMeasure17;
    @Ignore
    private String strMeasure18;
    @Ignore
    private String strMeasure19;
    @Ignore
    private String strMeasure20;



    public String youtubeId(){
        if (!strYoutube.isEmpty()&& strYoutube!=null){
            String id[] = strYoutube.split("v=");
            return id[1];
        }
        return " ";

    }
    @Ignore
    private List<String> measurement;
    @Ignore
    private List<String> ingredient;

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public void setIngredientAndMeasurement() {
        ingredient = new ArrayList<>();
        measurement = new ArrayList<>();
        if (strIngredient1 != null && !strIngredient1.isEmpty()) {
            ingredient.add(strIngredient1);
            measurement.add(strMeasure1);
        }else {
            return;
        }
        if (strIngredient2 != null && !strIngredient2.isEmpty()) {
            ingredient.add(strIngredient2);
            measurement.add(strMeasure2);
        }else {
            return;
        }
        if (strIngredient3 != null && !strIngredient3.isEmpty()) {
            ingredient.add(strIngredient3);
            measurement.add(strMeasure3);
        }else {
            return;
        }
        if (strIngredient4 != null && !strIngredient4.isEmpty()) {
            ingredient.add(strIngredient4);
            measurement.add(strMeasure4);
        }else {
            return;
        }
        if (strIngredient5 != null && !strIngredient5.isEmpty()) {
            ingredient.add(strIngredient5);
            measurement.add(strMeasure5);
        }else {
            return;
        }
        if (strIngredient6 != null && !strIngredient6.isEmpty()) {
            ingredient.add(strIngredient6);
            measurement.add(strMeasure6);
        }else {
            return;
        }
        if (strIngredient7 != null && !strIngredient7.isEmpty()) {
            ingredient.add(strIngredient7);
            measurement.add(strMeasure7);
        }else {
            return;
        }
        if (strIngredient8 != null && !strIngredient8.isEmpty()) {
            ingredient.add(strIngredient8);
            measurement.add(strMeasure8);
        }else {
            return;
        }
        if (strIngredient9 != null && !strIngredient9.isEmpty()) {
            ingredient.add(strIngredient9);
            measurement.add(strMeasure9);
        }else {
            return;
        }
        if (strIngredient10 != null && !strIngredient10.isEmpty()) {
            ingredient.add(strIngredient10);
            measurement.add(strMeasure10);
        }else {
            return;
        }
        if (strIngredient11 != null && !strIngredient11.isEmpty()) {
            ingredient.add(strIngredient11);
            measurement.add(strMeasure11);
        }else {
            return;
        }
        if (strIngredient12 != null && !strIngredient12.isEmpty()) {
            ingredient.add(strIngredient12);
            measurement.add(strMeasure12);
        }else {
            return;
        }
        if (strIngredient13 != null && !strIngredient13.isEmpty()) {
            ingredient.add(strIngredient13);
            measurement.add(strMeasure13);
        }else {
            return;
        }
        if (strIngredient14 != null && !strIngredient14.isEmpty()) {
            ingredient.add(strIngredient14);
            measurement.add(strMeasure14);
        }else {
            return;
        }
        if (strIngredient15 != null && !strIngredient15.isEmpty()) {
            ingredient.add(strIngredient15);
            measurement.add(strMeasure15);
        }else {
            return;
        }
        if (strIngredient16 != null && !strIngredient16.isEmpty()) {
            ingredient.add(strIngredient16);
            measurement.add(strMeasure16);
        }else {
            return;
        }
        if (strIngredient17 != null && !strIngredient17.isEmpty()) {
            ingredient.add(strIngredient17);
            measurement.add(strMeasure17);
        }else {
            return;
        }
        if (strIngredient18 != null && !strIngredient18.isEmpty()) {
            ingredient.add(strIngredient18);
            measurement.add(strMeasure18);
        }else {
            return;
        }
        if (strIngredient19 != null && !strIngredient19.isEmpty()) {
            ingredient.add(strIngredient19);
            measurement.add(strMeasure19);
        }else {
            return;
        }
        if (strIngredient20 != null && !strIngredient20.isEmpty()) {
            ingredient.add(strIngredient20);
            measurement.add(strMeasure20);
        }else {
            return;
        }


    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public List<String> getMeasurement(){
        return measurement;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "idMeal='" + idMeal + '\'' +
                ", strMeal='" + strMeal + '\'' +
                ", strMealThumb='" + strMealThumb + '\'' +
                '}';
    }
}

