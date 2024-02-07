package com.example.foodplanner.Network;




import com.example.foodplanner.MainScreen.model.Category;

import java.util.List;

public interface NetworkCallback {
   public void onSuccessResults(List<Category> categories);
   public void onFailureResult(String msg);
}
