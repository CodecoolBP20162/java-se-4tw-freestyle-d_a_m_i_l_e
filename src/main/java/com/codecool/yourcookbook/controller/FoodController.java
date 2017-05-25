package com.codecool.yourcookbook.controller;
import com.codecool.yourcookbook.dao.FoodDaoWithJDBC;
import com.codecool.yourcookbook.model.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class FoodController {
    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    static FoodDaoWithJDBC foodDaoWithJDBC = new FoodDaoWithJDBC();

    public static ModelAndView renderAllRecipes() {
        Map params = new HashMap<>();
        params.put("Food", foodDaoWithJDBC.getAll());
        logger.debug("Food instances had been added successfully.");
        return new ModelAndView(params, "index" );
    }

    public static ModelAndView renderShowRecipe(String id) {
        int intId = Integer.parseInt(id);
        Map params = new HashMap();
        params.put("recipe", foodDaoWithJDBC.findById(intId));
        logger.debug("Food instances with ID {} had been added successfully.", id);
        return new ModelAndView(params, "show_recipe");
    }

    public static ModelAndView renderFilterByCategory(String category) {
        switch (category) {
            case "main_course":
                category = "Main course";
                logger.debug("Food category '{}' has been chosen by the user", category);
                break;
            case "starter_soup":
                category = "Starter/Soup";
                logger.debug("Food category '{}' has been chosen by the user", category);//
                break;
            case "dessert":
                category = "Dessert";
                logger.debug("Food category '{}' has been chosen by the user", category);//
                break;
            case "new_recipe":
                renderNewRecipe();
        }
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.filterByCategory(category));
        logger.debug("Filtering by Food category '{}' has been made successfully", category);
        return new ModelAndView(params, "index");
    }

    public static ModelAndView renderNewRecipe() {
        Map params = new HashMap();
        params.put("Food", new Food());
        params.put("header", "New Recipe");
        params.put("button", "Create Recipe");
        return new ModelAndView(params, "form");
    }

    public static ModelAndView AddNewRecipe(Request req, Response res) {
        int id = foodDaoWithJDBC.getAll().size();
        String imageName = req.queryParams("imageName");
        if(imageName.equals("")) {
            logger.debug("User added new  recipe but didn't upload an image.");
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
        logger.debug("New recipe has been added. Details: {}", food);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.getAll());
        logger.debug("Recipes are successfully updated with the new recipe.");
        return new ModelAndView(params, "index" );

    }

    public static ModelAndView DeleteRecipe(String id) {
        int intId = Integer.parseInt(id);
        foodDaoWithJDBC.delete(intId);
        logger.debug("Food with ID '{}' has been deleted by the user", intId);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.getAll());
        logger.debug("Food instances have been updated after the deletion");
        return new ModelAndView(params, "index");
    }

    public static ModelAndView renderEditRecipe(String id) {
        int intId = Integer.parseInt(id);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.findById(intId));
        params.put("header", "Edit Recipe");
        params.put("button", "Update Recipe");
        logger.debug("Recipe with ID '{}' has been chosen to edit", intId);
        return new ModelAndView(params, "form");
    }

    public static ModelAndView UpdateRecipe(String id, Request req) {
        int intId = Integer.parseInt(id);
        foodDaoWithJDBC.update(intId, req);
        logger.debug("Recipe with ID '{}' has been updated by the user", intId);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.getAll());
        logger.debug("Food instances have been are now updated after the update");
        return new ModelAndView(params, "index");

    }

    public static ModelAndView search(String substring) {
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.search(substring));
        logger.debug("User searched for recipes which contain '{}'", substring);
        return new ModelAndView(params, "index");
    }

}