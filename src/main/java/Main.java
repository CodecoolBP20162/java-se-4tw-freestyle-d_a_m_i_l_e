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

        post("/recipe/0", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.AddNewRecipe(req, res));
        });

        get("/delete/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.DeleteRecipe(req.params(":id")));
        });

        get("/recipe/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.renderEditRecipe(req.params(":id")));
        });

        post("/recipe/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.UpdateRecipe(req.params(":id"), req));
        });

        post("/search/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(FoodController.search(req.queryParams("search")));
        });

    }

}