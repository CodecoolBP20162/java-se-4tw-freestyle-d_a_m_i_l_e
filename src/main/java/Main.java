import com.codecool.yourcookbook.controller.FoodController;
import spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8884);

        get("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.renderAllRecipes());
        });

        get("/show_recipe/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.renderShowRecipe(req.params(":id")));
        });

        get("/:name", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.renderFilterByCategory(req.params(":name")));
        });

        get("/new_recipe/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.renderNewRecipe());
        });

        post("/add/new_recipe/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.renderAddNewRecipe(req, res));
        });

        get("/delete/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.renderDeleteRecipe(req.params(":id")));
        });

    }

}