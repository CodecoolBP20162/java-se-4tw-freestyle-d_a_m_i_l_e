package com.codecool.yourcookbook.dao;

import com.codecool.yourcookbook.model.Food;
import spark.Request;

import java.util.Stack;

/**
 * <h1>FoodDao interface</h1> for managing Food instances
 */
public interface FoodDao {
    /**
     * Gets all Food instances
     *
     * @return a Stack with all Food instances
     */
    Stack<Food> getAll();

    /**
     * Gets all Food instances filtered by the given Category
     *
     * @param category category of the Food
     * @return a Stack with the Food objects filtered by the given category
     */
    Stack<Food> filterByCategory(String category);

    /**
     * Gets the Food instance which has the same ID as the given
     *
     * @param id ID of the Food
     * @return Food object by the given ID
     */
    Food findById(int id);

    /**
     * Adds a new Food instance to the database table
     *
     * @param food the new Food instance
     */
    void add(Food food);

    /**
     * Deletes a Food instance by the given ID
     *
     * @param id ID of the Food instance to be deleted
     */
    void delete(int id);

    /**
     * Updates the Food instance with the given ID in the database
     *
     * @param id  ID of the Food instance to be updated
     * @param req the information about the HTTP request
     */
    void update(int id, Request req);

    /**
     * Searches a Food instance that contains the given String
     *
     * @param substring String which the Food instance should contain
     * @return Stack of the Food instances which contain the given String
     */
    Stack<Food> search(String substring);


}