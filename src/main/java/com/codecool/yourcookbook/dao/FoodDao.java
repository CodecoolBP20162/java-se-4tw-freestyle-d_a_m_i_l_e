package com.codecool.yourcookbook.dao;

import com.codecool.yourcookbook.model.Food;
import java.util.Stack;

public interface FoodDao {

    public Stack<Food> getAll();
    public Stack<Food> filterByCategory(String category);
    public Food findById(int id);


}