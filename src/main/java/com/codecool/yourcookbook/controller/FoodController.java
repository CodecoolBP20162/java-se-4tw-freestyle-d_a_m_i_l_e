package com.codecool.yourcookbook.controller;
import com.codecool.yourcookbook.dao.FoodDaoWithJDBC;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class FoodController {

    static FoodDaoWithJDBC foodDaoWithJDBC = new FoodDaoWithJDBC();

    public static ModelAndView renderAllRecipes() {
        Map params = new HashMap<>();
        params.put("Food", foodDaoWithJDBC.getAll());
        return new ModelAndView(params, "index" );
    }

    public static ModelAndView renderShowRecipe(String id) {
        int intId = Integer.parseInt(id);
        Map params = new HashMap();
        params.put("recipe", foodDaoWithJDBC.findById(intId));
        return new ModelAndView(params, "show_recipe");
    }

    public static ModelAndView renderFilterByCategory(String category) {
        System.out.println(category);
        switch (category) {
            case "main_course":
                category = "Main course";
                break;
            case "starter_soup":
                category = "Starter/Soup";
                break;
            case "dessert":
                category = "Dessert";
                break;
            case "new_recipe":
                renderNewRecipe();
        }
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.filterByCategory(category));
        return new ModelAndView(params, "index");
    }

    public static ModelAndView renderNewRecipe() {
        Map params = new HashMap();
        params.put("header", "New Recipe");
        params.put("button", "Create Recipe");
        return new ModelAndView(params, "form");
    }


}