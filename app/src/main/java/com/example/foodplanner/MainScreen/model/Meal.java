package com.example.foodplanner.MainScreen.model;


public class Meal {
    private String idMeal;
    private String strMeal;

    private String strCategory;
    private String strArea;
    private String strInstructions;

    private String strMealThumb;

//    public String strYoutube;
//    public String strIngredient1;
//    public String strIngredient2;
//    public String strIngredient3;
//    public String strIngredient4;
//    public String strIngredient5;
//    public String strIngredient6;
//    public String strIngredient7;
//    public String strIngredient8;
//    public String strIngredient9;
//    public String strIngredient10;
//    public String strIngredient11;
//    public String strIngredient12;
//    public String strIngredient13;
//    public String strIngredient14;
//    public String strIngredient15;
//    public String strIngredient16;
//    public String strIngredient17;
//    public String strIngredient18;
//    public String strIngredient19;
//    public String strIngredient20;
//    public String strMeasure1;
//    public String strMeasure2;
//    public String strMeasure3;
//    public String strMeasure4;
//    public String strMeasure5;
//    public String strMeasure6;
//    public String strMeasure7;
//    public String strMeasure8;
//    public String strMeasure9;
//    public String strMeasure10;
//    public String strMeasure11;
//    public String strMeasure12;
//    public String strMeasure13;
//    public String strMeasure14;
//    public String strMeasure15;
//    public String strMeasure16;
//    public String strMeasure17;
//    public String strMeasure18;
//    public String strMeasure19;
//    public String strMeasure20;

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

