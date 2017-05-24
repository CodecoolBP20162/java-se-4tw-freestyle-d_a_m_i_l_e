package com.codecool.yourcookbook.controller;
import com.codecool.yourcookbook.dao.FoodDaoWithJDBC;
import com.codecool.yourcookbook.model.Food;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
        params.put("Food", new Food());
        params.put("header", "New Recipe");
        params.put("button", "Create Recipe");
        return new ModelAndView(params, "form");
    }

    public static ModelAndView renderAddNewRecipe(Request req, Response res) {
        int id = foodDaoWithJDBC.getAll().size();
        String imageName = req.queryParams("imageName");
        if(imageName.equals("")) {
            imageName = "yourcookbook.png";
        }
        Food food = new Food(
                id,
                req.queryParams("name"),
                req.queryParams("ingredients"),
                req.queryParams("recipe"),
                req.queryParams("category"),
                imageName
                );
        foodDaoWithJDBC.add(food);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.getAll());
        return new ModelAndView(params, "index" );

    }

    public static ModelAndView renderDeleteRecipe(String id) {
        int intId = Integer.parseInt(id);
        foodDaoWithJDBC.delete(intId);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.getAll());
        return new ModelAndView(params, "index");
    }

    public static ModelAndView rendeEditRecipe(String id) {
        int intId = Integer.parseInt(id);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.findById(intId));
        params.put("header", "Edit Recipe");
        params.put("button", "Update Recipe");
        return new ModelAndView(params, "form");
    }

    public static ModelAndView UpdateRecipe(String id, Request req) {
        int intId = Integer.parseInt(id);
        foodDaoWithJDBC.update(intId, req);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.getAll());
        return new ModelAndView(params, "index");

    }

}