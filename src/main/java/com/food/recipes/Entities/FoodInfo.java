/**
 * 
 */
package com.food.recipes.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Ankitha
 *
 */
@Entity
@Table(name="FOOD_INFO")
public class FoodInfo {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer dishId;
	@Column(name="DISH_NAME",length=100, nullable=false)
	private String dishName;
	@Column(name="RECIPE",length=500, nullable=false)
	private String recipe;
	@Column(name="FOOD_TYPE",length=10)
	private String foodType;
	@Column(name="NUM_OF_SERVINGS",length=5)
	private Integer numOfServings;
	
	
	public FoodInfo(String dishName, Integer dishId, String recipe, String foodType, Integer numOfServings) {
		super();
		this.dishName = dishName;
		this.dishId = dishId;
		this.recipe = recipe;
		this.foodType = foodType;
		this.numOfServings = numOfServings;
	}
	
	public FoodInfo() {
		super();
	}

	@Override
	public String toString() {
		return "FoodInfo [dishName=" + dishName + ", dishId=" + dishId + ", recipe=" + recipe + ", foodType=" + foodType
				+ ", numOfServings=" + numOfServings + "]";
	}

	/**
	 * @return the dishName
	 */
	public String getDishName() {
		return dishName;
	}
	/**
	 * @param dishName the dishName to set
	 */
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	/**
	 * @return the dishId
	 */
	public Integer getDishId() {
		return dishId;
	}
	/**
	 * @param dishId the dishId to set
	 */
	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}
	/**
	 * @return the recipe
	 */
	public String getRecipe() {
		return recipe;
	}
	/**
	 * @param recipe the recipe to set
	 */
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	/**
	 * @return the foodType
	 */
	public String getFoodType() {
		return foodType;
	}
	/**
	 * @param foodType the foodType to set
	 */
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	/**
	 * @return the numOfServings
	 */
	public Integer getNumOfServings() {
		return numOfServings;
	}
	/**
	 * @param numOfServings the numOfServings to set
	 */
	public void setNumOfServings(Integer numOfServings) {
		this.numOfServings = numOfServings;
	}
	
	
	
}
