package com.codecool.yourcookbook.dao;

import com.codecool.yourcookbook.connection.JDBCConnection;
import com.codecool.yourcookbook.model.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

public class FoodDaoWithJDBC  extends JDBCConnection implements FoodDao {
    private static final Logger logger = LoggerFactory.getLogger(FoodDaoWithJDBC.class);

    @Override
    public Stack<Food> getAll(){
        String query = "SELECT * FROM food ORDER BY name ASC;";
        Stack<Food> allFood = getFoodStack(query);
        return allFood;
    }

    @Override
    public Stack<Food> filterByCategory(String category) {
        String query = "SELECT * FROM food WHERE category = '" + category + "';";
        Stack<Food> foodStack = getFoodStack(query);
        return foodStack;
    }

    @Override
    public Food findById(int id) {
        String query = "SELECT * FROM food WHERE id ='" + id + "';";
        try {
            ResultSet resultSet = executeQuery(query);
            while (resultSet.next()) {
                Food food = createFood(resultSet);
                return food;
            }
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Food food) {
        String query = "INSERT INTO food (name, ingredients, recipe, category, imageName)" +
                "VALUES ('" + food.getName() + "', '" + food.getIngredients() + "', '" + food.getRecipe() +
                "', '" + food.getCategory() + "', '" + food.getImageName() + "');";
        try {
            executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM food WHERE id = '" + id +"';";
        try {
            executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, spark.Request req) {
        Food food = findById(id);
        System.out.println(food.getName());
        String query = "UPDATE food SET " +
                "name = '" + req.queryParams("name") + "', " +
                "ingredients = '" + req.queryParams("ingredients") + "', " +
                "recipe = '" + req.queryParams("recipe") + "', " +
                "category = '" + req.queryParams("category") + "', " +
                "imageName = '" + req.queryParams("imageName") + "'" +
                " WHERE id = '" + food.getId() + "';";
        try {
            executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stack<Food> search(String substring) {
        String query = "SELECT * FROM food WHERE LOWER(name) LIKE '%" + substring + "%';";
        Stack<Food> foodStack = getFoodStack(query);
        System.out.println(foodStack);
        return foodStack;

    }

    public Food createFood(ResultSet resultSet) throws SQLException {
        Food food = new Food(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("ingredients"),
                resultSet.getString("recipe"),
                resultSet.getString("category"),
                resultSet.getString("imageName")
        );
        return food;
    }

    public Stack<Food> getFoodStack(String query) {
        Stack<Food> foodStack = new Stack<>();

        try {
            ResultSet resultSet = executeQuery(query);
            while (resultSet.next()) {
                Food food = createFood(resultSet);
                foodStack.push(food);
            }
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodStack;
    }
}


