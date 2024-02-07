package com.example.foodplanner.MainScreen.View;

import com.example.foodplanner.MainScreen.model.Category;

import java.util.List;

public interface ICategory {

 public void showData(List<Category> products);

 public void showErrorMessage(String error);

}
