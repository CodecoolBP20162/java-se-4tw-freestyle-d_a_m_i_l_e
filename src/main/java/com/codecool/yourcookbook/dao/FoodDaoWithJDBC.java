package com.codecool.yourcookbook.dao;

import com.codecool.yourcookbook.connection.JDBCConnection;
import com.codecool.yourcookbook.model.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

/**
 * <h1>FoodDaoWithJDBC class</h1> for creating queries. <p>It implements the FoodDao interface and inherits from the JDBCConnection class</p>
 */
public class FoodDaoWithJDBC extends JDBCConnection implements FoodDao {
    private static final Logger logger = LoggerFactory.getLogger(FoodDaoWithJDBC.class);

    /**
     * Gets all Food instances
     *
     * @return a Stack with all Food instances
     */

    @Override
    public Stack<Food> getAll() {
        String query = "SELECT * FROM food ORDER BY name ASC;";
        Stack<Food> allFood = getFoodStack(query);
        return allFood;
    }

    /**
     * Gets all Food instances filtered by the given Category
     *
     * @param category category of the Food
     * @return a Stack with the Food objects filtered by the given category
     */
    @Override
    public Stack<Food> filterByCategory(String category) {
        String query = "SELECT * FROM food WHERE category = '" + category + "';";
        Stack<Food> foodStack = getFoodStack(query);
        return foodStack;
    }

    /**
     * Gets the Food instance which has the same ID as the given
     *
     * @param id ID of the Food
     * @return Food object by the given ID
     */
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

    /**
     * Adds a new Food instance to the database table
     *
     * @param food the new Food instance
     */
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

    /**
     * Deletes a Food instance by the given ID
     *
     * @param id ID of the Food instance to be deleted
     */
    @Override
    public void delete(int id) {
        String query = "DELETE FROM food WHERE id = '" + id + "';";
        try {
            executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the Food instance with the given ID in the database
     *
     * @param id  ID of the Food instance to be updated
     * @param req the information about the HTTP request
     */
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

    /**
     * Searches a Food instance that contains the given String
     *
     * @param substring String which the Food instance should contain
     * @return Stack of the Food instances which contain the given String
     */
    @Override
    public Stack<Food> search(String substring) {
        String query = "SELECT * FROM food WHERE LOWER(name) LIKE '%" + substring + "%';";
        Stack<Food> foodStack = getFoodStack(query);
        System.out.println(foodStack);
        return foodStack;

    }

    /**
     * Creates Food instances
     *
     * @param resultSet Result set of the generated query
     * @return Food instance
     * @throws SQLException If database access error occurs or other errors
     */
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

    /**
     * Gets the Stack with the Food instances by the given SQL query
     *
     * @param query a String to be used as a query
     * @return the Stack of the Food instances after the executed query
     */
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


