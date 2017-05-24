package com.codecool.yourcookbook.model;

public class Food {
    private int id;
    private String name;
    private String ingredients;
    private String recipe;
    private String category;
    private String imageName;

    public Food(int id, String name, String ingredients, String recipe, String category, String imageName){
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.category = category;
        this.imageName = imageName;
    }

    public void setId(int id) { this.id = id;}

    public int getId() { return this.id;}

    public void setName(String name) { this.name = name;}

    public String getName() { return this.name;}

    public void setIngredients(String ingredients) {this.ingredients = ingredients;}

    public String getIngredients(){ return this.ingredients;}

    public void setRecipe(String recipe) { this.recipe = recipe;}

    public String getRecipe() {return this.recipe;}

    public void setCategory() {this.category = category;}

    public String getCategory() { return  this.category;}

    public void setImageName(String imageName) { this.imageName = imageName;}

    public String getImageName() { return this.imageName;}

}