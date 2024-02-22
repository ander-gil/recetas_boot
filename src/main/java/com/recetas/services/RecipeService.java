package com.recetas.services;

import com.recetas.model.Recipe;
import com.recetas.model.User;
import com.recetas.repository.RecipeRepository;

import java.util.List;

public interface RecipeService {

    public Recipe cretaeRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id)throws Exception;

    public void deleteRecipe(Long id)throws Exception;

    public Recipe updateRecipe(Recipe recipe, Long id)throws Exception;

    public List<Recipe> findAllRecipe();

    public Recipe likeRecipe(Long recipeId, User user)throws Exception;

}
