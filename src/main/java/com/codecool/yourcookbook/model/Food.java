package com.codecool.yourcookbook.model;

/**
 * <h1>Food class</h1> for managing Food instances
 */
public class Food {
    /**
     * ID of the Food instance
     */
    private int id;
    /**
     * Name of the Food instance
     */
    private String name;
    /**
     * Ingredients of the Food instance
     */
    private String ingredients;
    /**
     * Recipe of the Food instance
     */
    private String recipe;
    /**
     * Category of the Food instance
     */
    private String category;
    /**
     * Image name of the Food instance
     */
    private String imageName;

    /**
     * The constructor of the Food class
     */
    public Food() {
    }

    ;

    /**
     * Constructor of the Food class with parameters
     *
     * @param id          ID of the Food instance
     * @param name        Name of the Food instance
     * @param ingredients Ingredients of the Food instance
     * @param recipe      Recipe of the Food instance
     * @param category    Category of the Food instance
     * @param imageName   Image name of the Food instance
     */
    public Food(int id, String name, String ingredients, String recipe, String category, String imageName) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.category = category;
        this.imageName = imageName;
    }

    /**
     * Sets the ID of the Food instance
     *
     * @param id Id of the Food instance
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the Food instance
     *
     * @return ID of the Food instance
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the Name of the Food instance
     *
     * @param name Name of the Food instance
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the Food instance
     *
     * @return Name of the Food instance
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the ingredients of the Food instance
     *
     * @param ingredients Ingredients of the Food instance
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets the ingredients of the Food instance
     *
     * @return Ingredients of the Food instance
     */
    public String getIngredients() {
        return this.ingredients;
    }

    /**
     * Sets the recipe of the Food instance
     *
     * @param recipe Recipe of the Food instance
     */
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    /**
     * Gets the recipe of the Food instance
     *
     * @return Recipe of the Food instance
     */
    public String getRecipe() {
        return this.recipe;
    }

    /**
     * Sets the category of the Food instance
     */
    public void setCategory() {
        this.category = category;
    }

    /**
     * Gets the category of the Food instance
     *
     * @return Category of the Food instance
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Sets the image name of the Food instance
     *
     * @param imageName Image name of the Food instance
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Gets the image name of the Food instance
     *
     * @return Image name of the Food instance
     */
    public String getImageName() {
        return this.imageName;
    }

}