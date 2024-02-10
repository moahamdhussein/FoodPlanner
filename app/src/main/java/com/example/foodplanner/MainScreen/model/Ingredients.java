package com.example.foodplanner.MainScreen.model;

public class Ingredients {
    public String idIngredient;
    public String strIngredient;

    public String getIdIngredient() {
        return idIngredient;
    }


    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "idIngredient='" + idIngredient + '\'' +
                ", strIngredient='" + strIngredient + '\'' +
                '}';
    }
}