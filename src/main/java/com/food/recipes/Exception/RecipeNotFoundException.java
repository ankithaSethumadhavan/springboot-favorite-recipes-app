package com.food.recipes.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}