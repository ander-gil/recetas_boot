package com.recetas.controller;

import com.recetas.model.Recipe;
import com.recetas.model.User;
import com.recetas.services.RecipeService;
import com.recetas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwt(jwt);
       Recipe createdRecipe = recipeService.cretaeRecipe(recipe, user);
       return createdRecipe;
    }

    @GetMapping()
    public List<Recipe> getAllRecipes(){
        return recipeService.findAllRecipe();
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipes(@PathVariable Long recipeId) {
        try {
            recipeService.deleteRecipe(recipeId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            // Manejo de excepciones, devolviendo un estado de error genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id)throws Exception{
        Recipe recipeUpdate = recipeService.updateRecipe(recipe,id);
        return recipeUpdate;
    }

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@PathVariable Long id, @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwt(jwt);
        Recipe recipeUpdate = recipeService.likeRecipe(id,user);
        return recipeUpdate;
    }
}
