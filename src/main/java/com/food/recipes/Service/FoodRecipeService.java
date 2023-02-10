package com.food.recipes.Service;

import static java.util.Objects.nonNull;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.recipes.Entities.FoodInfo;
import com.food.recipes.Exception.RecipeNotFoundException;
import com.food.recipes.Repository.FoodRepository;
@Service
@Transactional
public class FoodRecipeService {

	@Autowired
   FoodRepository foodRepository;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FoodRecipeService.class);
	
	 @SuppressWarnings("rawtypes")
	public List fetchAllFoodList() throws RecipeNotFoundException {
	        logger.debug("getAllEmployees method started");
	        List<FoodInfo> recipeList = foodRepository.findAll();
	        if (recipeList.size() > 0)
	            return recipeList;
	        else
	        	throw new RecipeNotFoundException("No recipe records exist");
	    }

	    public FoodInfo fetchFoodDetailsById(Integer dishId) throws RecipeNotFoundException {
	        logger.debug("fetchFoodDetails method started {}",dishId);
	        FoodInfo foodDtls = foodRepository.findById(dishId).orElse(null);
	        if (nonNull(foodDtls))
	            return foodDtls;
	        else
	            throw new RecipeNotFoundException("No recipe record exist for given id");
	    }
	    
	    public List<FoodInfo> fetchFoodDetailsByParams(FoodInfo foodDetails) throws RecipeNotFoundException {
	    		String dishName= foodDetails.getDishName();
	    		String foodType= foodDetails.getFoodType();
	    		String recipe= foodDetails.getRecipe();
	    		Integer numOfServings= (foodDetails.getNumOfServings()!=null?foodDetails.getNumOfServings():0);
		        List<FoodInfo> foodDtls = foodRepository.findRecipeListsBasedOnParams(dishName,foodType,recipe,numOfServings);
		        if (nonNull(foodDtls) && foodDtls.size()>0)
		            return foodDtls;
		        else
		            throw new RecipeNotFoundException("No recipe record exist for given id for the given parameters");
		}
	 
	    public FoodInfo addOrUpdateRecipe(FoodInfo foodDetails)  throws RecipeNotFoundException {
	    	logger.debug("addOrUpdateRecipe method started {}",foodDetails);
	    	FoodInfo recipeRecord = foodRepository.findById(foodDetails.getDishId()).orElse(null);
	        if (nonNull(recipeRecord)) {
	        	logger.debug("update the record");
	        	recipeRecord.setDishId(foodDetails.getDishId());
	        	recipeRecord.setDishName(foodDetails.getDishName());
	        	recipeRecord.setFoodType(foodDetails.getFoodType());
	        	recipeRecord.setNumOfServings(foodDetails.getNumOfServings());
	        	recipeRecord.setRecipe(foodDetails.getRecipe());
	            return foodRepository.save(recipeRecord);
	        } else {
	        	logger.debug("save the record");
	            return foodRepository.save(foodDetails);
	        }
	    }

	    public FoodInfo deleteFoodDtls(Integer id) throws RecipeNotFoundException {
	    	logger.debug("deleteFoodDtls method started {}",id);
	    	FoodInfo foodDtls = foodRepository.findById(id).orElse(null);
	        if (nonNull(foodDtls)) {
	        	 foodRepository.deleteById(id);
            	return foodDtls;
	        }
	        else
	            throw new RecipeNotFoundException("No recipe record exist for given dish id: "+id);
	    }
	    
	    public String validateInputRequest(FoodInfo foodDetails) {
	    	
	    	return "";
	    }

		
}
