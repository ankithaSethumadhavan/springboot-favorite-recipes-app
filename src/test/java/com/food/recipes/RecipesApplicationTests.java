package com.food.recipes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.food.recipes.Controller.FoodRecipeController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipesApplicationTests {
	
	@Autowired
	FoodRecipeController foodRecipeController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(foodRecipeController).isNotNull();
	}
}
