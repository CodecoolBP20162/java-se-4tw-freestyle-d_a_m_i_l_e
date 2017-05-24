package com.codecool.yourcookbook.dao;

import com.codecool.yourcookbook.model.Food;
import java.util.Stack;

public interface FoodDao {

    Stack<Food> getAll();
    Stack<Food> filterByCategory(String category);
    Food findById(int id);
    void add(Food food);



}