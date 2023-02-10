package com.food.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.food.recipes.Controller.FoodRecipeController;
import com.food.recipes.Entities.FoodInfo;
import com.food.recipes.Service.FoodRecipeService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {RecipesAppTestController.class})
public class RecipesAppTestController {

	@Mock
	FoodRecipeService foodRecipeService;

	@InjectMocks
	FoodRecipeController foodRecipeController;
	
	List<FoodInfo> foodDtlsList;
	FoodInfo FoodInfo;

	@Test
	@Order(1)
	public void testfindAll() throws Exception  {
		 FoodInfo foodDtls = new FoodInfo("Carrot Salad",2, "Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts",
					"veg",2);
		List<FoodInfo> foodDtlsList = Arrays.asList(foodDtls);

		when(foodRecipeService.fetchAllFoodList()).thenReturn(foodDtlsList);

		ResponseEntity<Object> result = foodRecipeController.fetchAllFoodList();
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void testfindById() throws Exception  {
		 FoodInfo foodDtls = new FoodInfo("Carrot Salad",2, "Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts",
					"veg",2);
		 int dishId=2;
		when(foodRecipeService.fetchFoodDetailsById(foodDtls.getDishId())).thenReturn(foodDtls);

		ResponseEntity<Object> result = foodRecipeController.fetchFoodDetailsById(foodDtls.getDishId());
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(dishId, foodDtls.getDishId());
	}
	
	@Test
	@Order(3)
	public void testaddRecipe() throws Exception  {
		 FoodInfo recipe = new FoodInfo("Pepper Chicken",4, "This simplified version of the Indian classic combines chicken, pepper, and a slew of aromatic spices and herbs",
					"nonveg",3);
	         
		when(foodRecipeService.addOrUpdateRecipe(recipe)).thenReturn(recipe);

		ResponseEntity<Object> result = foodRecipeController.createOrUpdateRecipe(recipe);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		//assertEquals(recipe, result.getBody());
	}
	
	@Test
	@Order(4)
	public void testdeleteRecipe() throws Exception  {
		 FoodInfo recipe = new FoodInfo("Pepper Chicken",4, "This simplified version of the Indian classic combines chicken, pepper, and a slew of aromatic spices and herbs",
					"nonveg",3);
	         
		when(foodRecipeService.deleteFoodDtls(recipe.getDishId())).thenReturn(recipe);
		ResponseEntity<Object> result = foodRecipeController.deleteFoodDtls(recipe.getDishId());
		 foodRecipeService.deleteFoodDtls(recipe.getDishId());
		 assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
}
