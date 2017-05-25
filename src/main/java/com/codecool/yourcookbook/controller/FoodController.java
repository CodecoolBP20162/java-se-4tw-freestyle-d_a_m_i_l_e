package com.codecool.yourcookbook.controller;

import com.codecool.yourcookbook.dao.FoodDaoWithJDBC;
import com.codecool.yourcookbook.model.Food;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>FoodController class</h1> for rendering the needed data.
 */
public class FoodController {

    static FoodDaoWithJDBC foodDaoWithJDBC = new FoodDaoWithJDBC();

    /**
     * Renders all the recipes
     *
     * @return An instance with the provided HashMap of the Food instances and the "index" view name
     */
    public static ModelAndView renderAllRecipes() {
        Map params = new HashMap<>();
        params.put("Food", foodDaoWithJDBC.getAll());
        return new ModelAndView(params, "index");
    }

    /**
     * Renders the recipe by the given ID when clicked on it in the web browser
     *
     * @param id ID of the recipe
     * @return An instance with the provided HashMap where the Food instances will be shown when clicked on it and the "show_recipe" view name
     */
    public static ModelAndView renderShowRecipe(String id) {
        int intId = Integer.parseInt(id);
        Map params = new HashMap();
        params.put("recipe", foodDaoWithJDBC.findById(intId));
        return new ModelAndView(params, "show_recipe");
    }

    /**
     * Renders the categories which the recipes can be filtered by
     *
     * @param category Category of the Food instance
     * @return An instance with the provided HashMap where the filtered Food instance will be added by the chosen category and the "index" view name
     */
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

    /**
     * Renders the html form where the new recipe will be created
     *
     * @return An instance with the provided HashMap where the newly created Food instance will be put and the "form" view name
     */
    public static ModelAndView renderNewRecipe() {
        Map params = new HashMap();
        params.put("Food", new Food());
        params.put("header", "New Recipe");
        params.put("button", "Create Recipe");
        return new ModelAndView(params, "form");
    }

    /**
     * Shows all newly added recipes in the browser
     *
     * @param req Provides information about the HTTP request
     * @param res Provides functionality for modifying the response
     * @return An instance with the provided HashMap where the newly added Food instances are included and the "index" view name
     */
    public static ModelAndView AddNewRecipe(Request req, Response res) {
        int id = foodDaoWithJDBC.getAll().size();
        String imageName = req.queryParams("imageName");
        if (imageName.equals("")) {
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
        return new ModelAndView(params, "index");

    }

    /**
     * Renders those recipes which haven't been deleted
     *
     * @param id ID of the recipe to be deleted
     * @return An instance with the provided HashMap where the deleted Food instances are not shown anymore and the "index" view name
     */
    public static ModelAndView DeleteRecipe(String id) {
        int intId = Integer.parseInt(id);
        foodDaoWithJDBC.delete(intId);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.getAll());
        return new ModelAndView(params, "index");
    }

    /**
     * Renders the form where the recipes can be updated
     *
     * @param id ID of the recipe which will be updated
     * @return An instance with the provided HashMap where the Food instance which will be updated is shown and the "form" view name
     */
    public static ModelAndView renderEditRecipe(String id) {
        int intId = Integer.parseInt(id);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.findById(intId));
        params.put("header", "Edit Recipe");
        params.put("button", "Update Recipe");
        return new ModelAndView(params, "form");
    }

    /**
     * Renders all recipes after we updated one
     *
     * @param id  ID of the recipe which will be updated
     * @param req Provides information about the HTTP request
     * @return An instance with the provided HashMap where the Food instances are updated and the "index" view name
     */
    public static ModelAndView UpdateRecipe(String id, Request req) {
        int intId = Integer.parseInt(id);
        foodDaoWithJDBC.update(intId, req);
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.getAll());
        return new ModelAndView(params, "index");

    }

    /**
     * Renders all the recipes which contain the given String
     *
     * @param substring String which should contain the recipe
     * @return An instance with the provided HashMap where the Food instances contain the given String and the "index" view name
     */
    public static ModelAndView search(String substring) {
        Map params = new HashMap();
        params.put("Food", foodDaoWithJDBC.search(substring));
        return new ModelAndView(params, "index");
    }

}