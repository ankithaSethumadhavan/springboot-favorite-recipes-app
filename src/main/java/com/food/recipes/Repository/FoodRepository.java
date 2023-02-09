package com.food.recipes.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.food.recipes.Entities.FoodInfo;

@Transactional
@Repository
public interface FoodRepository extends JpaRepository<FoodInfo, Integer>  {


	  @Query(value = "SELECT * FROM food_info  WHERE food_type = :foodType "
	            + " OR dish_name = :dishName "
	            + " OR recipe = :recipe "
	            + " OR num_of_servings = :numOfServings ",  nativeQuery = true)
	public List<FoodInfo> findRecipeListsBasedOnParams(String dishName, String foodType, String recipe,
			String numOfServings);

}
