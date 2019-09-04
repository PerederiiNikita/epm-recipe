package com.epm.recipe.web_api.exception;

public class RecipeNotFoundException extends BusinessException {

    @Override
    public String name() {
        return "RecipeNotFoundException";
    }

    @Override
    public String description() {
        return "Recipe not found";
    }
}
