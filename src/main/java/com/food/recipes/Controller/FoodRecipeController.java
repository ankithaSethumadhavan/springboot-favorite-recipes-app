package com.food.recipes.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.recipes.Entities.FoodInfo;
import com.food.recipes.Exception.RecipeNotFoundException;
import com.food.recipes.Response.ResponseHandler;
import com.food.recipes.Service.FoodRecipeService;

@RestController
@RequestMapping("/food")
public class FoodRecipeController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(FoodRecipeController.class);

	@Autowired
	private FoodRecipeService foodRecipeService;

	@SuppressWarnings("unchecked")
	@GetMapping("/recipes")
	public ResponseEntity<Object> fetchAllFoodList() throws RecipeNotFoundException {
		try {
			logger.debug("getAllEmployees method started");
			List<FoodInfo> list = foodRecipeService.fetchAllFoodList();
			// if(list.size()<=0){
			//     throw new RecipeNotFoundException("Recipe list not found");
			// }
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, list);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}


	@GetMapping("/recipes/{id}")
	public ResponseEntity<Object> fetchFoodDetailsById(@PathVariable("id") Integer id) throws RecipeNotFoundException {
		try {
			logger.debug("fetchFoodDetails method started {}",id);
			FoodInfo entity = null;
			entity = foodRecipeService.fetchFoodDetailsById(id);
			return ResponseHandler.generateResponse("Successfully Retrieved data!", HttpStatus.OK, entity);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@PostMapping("/fetchRecipeByParams")
	public ResponseEntity<Object> fetchFoodDetailsByParams( @RequestBody FoodInfo foodDetails) throws RecipeNotFoundException {
		try {
			logger.debug("fetchFoodDetails method started {}",foodDetails);
			List<FoodInfo> entity = null;
			entity = foodRecipeService.fetchFoodDetailsByParams(foodDetails);
			return ResponseHandler.generateResponse("Successfully Retrieved data!", HttpStatus.OK, entity);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@PostMapping("/addRecipe")
	public ResponseEntity<Object> createOrUpdateRecipe( @RequestBody FoodInfo foodDetails) throws RecipeNotFoundException {
		try {
			logger.debug("createOrUpdateRecipe method started {}",foodDetails);
			FoodInfo updated = foodRecipeService.addOrUpdateRecipe(foodDetails);
			return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, updated);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
		// return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);
	}


	@DeleteMapping("/deleteRecipe/{dishId}")
	public ResponseEntity<Object> deleteFoodDtls(@PathVariable("dishId") Integer dishId) throws RecipeNotFoundException {
		try {
			logger.debug("deleteEmployeeById method started {}",dishId);
			FoodInfo foodDtls= foodRecipeService.deleteFoodDtls(dishId);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, foodDtls);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
		//return ResponseEntity.ok().headers(headers).build();
	}
}
