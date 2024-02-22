package com.recetas.controller;

import com.recetas.model.Recipe;
import com.recetas.model.User;
import com.recetas.services.RecipeService;
import com.recetas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId)throws Exception{
        User user = userService.findUserById(userId);
       Recipe createdRecipe = recipeService.cretaeRecipe(recipe, user);
       return createdRecipe;
    }

    @GetMapping()
    public List<Recipe> getAllRecipes(){
        return recipeService.findAllRecipe();
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipes(@PathVariable Long recipeId)throws Exception{
         recipeService.deleteRecipe(recipeId);
         return "receta eliminada con exito";
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id)throws Exception{
        Recipe recipeUpdate = recipeService.updateRecipe(recipe,id);
        return recipeUpdate;
    }

    @PutMapping("/{id}/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long id, @PathVariable Long userId)throws Exception{
        User user = userService.findUserById(userId);
        Recipe recipeUpdate = recipeService.likeRecipe(id,user);
        return recipeUpdate;
    }
}
