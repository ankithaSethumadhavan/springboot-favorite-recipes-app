package com.food.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.food.recipes.Entities.FoodInfo;
import com.food.recipes.Exception.RecipeNotFoundException;
import com.food.recipes.Repository.FoodRepository;
import com.food.recipes.Service.FoodRecipeService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {RecipeAppTestService.class})
public class RecipeAppTestService {

	@Mock
    FoodRepository foodRecipeDao;
	
	@InjectMocks
	FoodRecipeService foodRecipeService;
    
	public  List<FoodInfo> recipeList;
	
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    
	@Test
	@Order(1)
	public void testFindAllRecipes()
    {
    	try {
        List<FoodInfo> recipeList = new ArrayList<FoodInfo>();
        FoodInfo recipeOne = new FoodInfo("Butter Chicken",1, "This simplified version of the Indian classic combines chicken, tomato sauce, and a slew of aromatic spices and herbs",
				"nonveg","4");
        FoodInfo recipeTwo = new FoodInfo("Carrot Salad",2, "Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts",
				"veg","2");
        FoodInfo recipeThree = new FoodInfo("Mix Veg Jeera Salad",3, "A lovely mix of spinach, carrots and potatoes, brightened with the peppery flavor of cumin",
				"veg","1");
         
        recipeList.add(recipeOne);
        recipeList.add(recipeTwo);
        recipeList.add(recipeThree);
         
        when(foodRecipeDao.findAll()).thenReturn(recipeList);
         
        assertEquals(3, foodRecipeService.fetchAllFoodList().size());
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	@Order(2)
	public void testFindRecipeById()
    {
    	try {
        List<FoodInfo> recipeList = new ArrayList<FoodInfo>();
        FoodInfo recipeOne = new FoodInfo("Butter Chicken",1, "This simplified version of the Indian classic combines chicken, tomato sauce, and a slew of aromatic spices and herbs",
				"nonveg","4");
        FoodInfo recipeTwo = new FoodInfo("Carrot Salad",2, "Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts",
				"veg","2");
        FoodInfo recipeThree = new FoodInfo("Mix Veg Jeera Salad",3, "A lovely mix of spinach, carrots and potatoes, brightened with the peppery flavor of cumin",
				"veg","1");
         
        recipeList.add(recipeOne);
        recipeList.add(recipeTwo);
        recipeList.add(recipeThree);
        
        int dishId =1;
        when(foodRecipeDao.findAll()).thenReturn(recipeList);
         
        assertEquals(dishId, foodRecipeService.fetchFoodDetailsById(dishId).getDishId());
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
    }
    
	
	@Test
	@Order(3)
	public void testAddRecipes()
    {
    	try {
        FoodInfo recipe = new FoodInfo("Pepper Chicken",4, "This simplified version of the Indian classic combines chicken, pepper, and a slew of aromatic spices and herbs",
				"nonveg","3");
         
        
        when(foodRecipeDao.save(recipe)).thenReturn(recipe);
         
        assertEquals(recipe, foodRecipeService.addOrUpdateRecipe(recipe));
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	@Order(4)
	public void testDeleteRecipes()
    {
    	try {
        FoodInfo recipe = new FoodInfo("Pepper Chicken",4, "This simplified version of the Indian classic combines chicken, pepper, and a slew of aromatic spices and herbs",
				"nonveg","3");
        
        foodRecipeService.deleteFoodDtls(recipe.getDishId());
        verify(foodRecipeDao,times(1)).delete(recipe);
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
    }
}
