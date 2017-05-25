import com.codecool.yourcookbook.controller.FoodController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

/**
 * <h1>Main class</h1> for running the program
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Uses Thymeleaf to render html pages and includes the settings of the server
     *
     * @param args Contains the supplied command-line arguments as an array of String objects
     */

    public static void main(String[] args) {
        logger.info("Program has been started...");

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8884);

        get("/", (Request req, Response res) -> {
            logger.info("User sees now the homepage."); //works fine
            return new ThymeleafTemplateEngine().render(FoodController.renderAllRecipes());
        });

        get("/show_recipe/:id", (Request req, Response res) -> {
            logger.info("User clicked on the recipe of the food with ID Nr.: " + req.params(":id")); //works fine
            return new ThymeleafTemplateEngine().render(FoodController.renderShowRecipe(req.params(":id")));
        });

        get("/:name", (Request req, Response res) -> {
            logger.info("User filtered the recipes by: " + req.params(":name"));//works fine
            return new ThymeleafTemplateEngine().render(FoodController.renderFilterByCategory(req.params(":name")));
        });

        get("/new_recipe/", (Request req, Response res) -> {
            logger.info("User is now creating a new recipe..."); //works fine
            return new ThymeleafTemplateEngine().render(FoodController.renderNewRecipe());
        });

        post("/recipe/0", (Request req, Response res) -> {
            logger.info("User created a recipe successfully.");//works fine
            return new ThymeleafTemplateEngine().render(FoodController.AddNewRecipe(req, res));
        });

        get("/delete/:id", (Request req, Response res) -> {
            logger.info("User deleted recipe with ID Nr.: " + req.params(":id"));//works fine
            return new ThymeleafTemplateEngine().render(FoodController.DeleteRecipe(req.params(":id")));
        });

        get("/recipe/:id", (Request req, Response res) -> {
            logger.info("User is editing a recipe with ID Nr.: " + req.params(":id"));//works fine
            return new ThymeleafTemplateEngine().render(FoodController.renderEditRecipe(req.params(":id")));
        });

        post("/recipe/:id", (Request req, Response res) -> {
            logger.info("User edited successfully: recipe with ID Nr.: " + req.params(":id"));//works fine
            return new ThymeleafTemplateEngine().render(FoodController.UpdateRecipe(req.params(":id"), req));
        });

        post("/search/", (Request req, Response res) -> {
            logger.info("User is now searching for a recipe...");//works fine
            logger.info("And was looking for the expression: " + req.queryParams("search")); //works fine
            return new ThymeleafTemplateEngine().render(FoodController.search(req.queryParams("search")));
        });

    }

}