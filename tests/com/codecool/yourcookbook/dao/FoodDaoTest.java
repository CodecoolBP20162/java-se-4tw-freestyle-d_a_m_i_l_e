package com.codecool.yourcookbook.dao;

import com.codecool.yourcookbook.model.Food;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by szilarddavid on 2017.05.25..
 */
class FoodDaoTest {

    FoodDao foodDao = new FoodDaoWithJDBC();

    @Test
    public void testGetAll(){
        assertEquals(5, foodDao.getAll().size());
    }

    @Test
    public void testFilterByCategpry() {
        assertEquals(3, foodDao.filterByCategory("Main course").size());
    }

    @Test
    public void testFindById() {
        Food expected = new Food();
        expected.setName("Szaftos-lucskos steak");
        expected.setId(5);
        expected.setImageName("steak.jpg");
        expected.setIngredients("steak");
        expected.setRecipe("Ez egy troll recept, süsd ahogy akarod.");
        expected.setCategory("Main course");
        Food actual = foodDao.findById(5);
        assertTrue(expected.getName().equals(actual.getName()));
    }

}