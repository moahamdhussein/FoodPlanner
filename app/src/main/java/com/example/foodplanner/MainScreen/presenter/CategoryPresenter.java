package com.example.foodplanner.MainScreen.presenter;

import com.example.foodplanner.MainScreen.View.ICategory;
import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.CategoryRepository;
import com.example.foodplanner.Network.NetworkCallback;

import java.util.List;

public class CategoryPresenter implements NetworkCallback, ICategoryPresenter {

   private static final String TAG = "CategoryPresenter";

   private ICategory view;
   private CategoryRepository repository;


   public CategoryPresenter(ICategory view, CategoryRepository repository) {
      this.view = view;
      this.repository = repository;
   }

   @Override
   public void getCategory(){
      repository.getRemoteData(this);
   }

   @Override
   public void onSuccessResults(List<Category> categories) {
      view.showData(categories);
   }

   @Override
   public void onFailureResult(String msg) {
      view.showErrorMessage(msg);
   }
}
