package com.codecool.yourcookbook.dao;

import com.codecool.yourcookbook.connection.JDBCConnection;
import com.codecool.yourcookbook.model.Food;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

public class FoodDaoWithJDBC  extends JDBCConnection implements FoodDao {

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

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM food WHERE id = '" + id +"';";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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


