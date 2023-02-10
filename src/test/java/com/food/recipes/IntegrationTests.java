package com.food.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.food.recipes.Entities.FoodInfo;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class IntegrationTests {

	
	@Test
	@Order(1)
	public void addRecipeTest() throws JSONException {
		
		 FoodInfo recipeOne = new FoodInfo("Carrot Salad",1, "Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts",
					"Veg",4);
		 
		String expectedResp = "{\r\n"
				+ "    \"data\": {\r\n"
				+ "        \"dishId\": 1,\r\n"
				+ "        \"dishName\": \"Carrot Salad\",\r\n"
				+ "        \"recipe\": \"Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts\",\r\n"
				+ "        \"foodType\": \"Veg\",\r\n"
				+ "        \"numOfServings\": 4\r\n"
				+ "    },\r\n"
				+ "    \"message\": \"Successfully added data!\",\r\n"
				+ "    \"status\": 200\r\n"
				+ "}";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<FoodInfo> request = new HttpEntity<FoodInfo>(recipeOne,headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9099/food/addRecipe", request, String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expectedResp, response.getBody(), false);
	}
	
	@Test
	@Order(2)
	public void fetchRecipeByIdTest() throws JSONException {
		
		String expectedResp = "{\r\n"
				+ "    \"data\": {\r\n"
				+ "        \"dishId\": 1,\r\n"
				+ "        \"dishName\": \"Carrot Salad\",\r\n"
				+ "        \"recipe\": \"Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts\",\r\n"
				+ "        \"foodType\": \"Veg\",\r\n"
				+ "        \"numOfServings\": 4\r\n"
				+ "    },\r\n"
				+ "    \"message\": \"Successfully Retrieved data!\",\r\n"
				+ "    \"status\": 200\r\n"
				+ "}";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9099/food/recipes/1", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expectedResp, response.getBody(), false);
	}
	

	@Test
	@Order(3)
	public void fetchAllRecipesTest() throws JSONException {
		
		String expectedResp = "{\r\n"
				+ "    \"data\": [\r\n"
				+ "        {\r\n"
				+ "            \"dishId\": 1,\r\n"
				+ "            \"dishName\": \"Carrot Salad\",\r\n"
				+ "            \"recipe\": \"Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts\",\r\n"
				+ "            \"foodType\": \"Veg\",\r\n"
				+ "            \"numOfServings\": 4\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"message\": \"Successfully retrieved data!\",\r\n"
				+ "    \"status\": 200\r\n"
				+ "}";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9099/food/recipes", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expectedResp, response.getBody(), false);
	}

	@Test
	@Order(4)
	public void fetchRecipeByParamsTest() throws JSONException {
		
		 FoodInfo recipeOne = new FoodInfo("",0, "","Veg",0);
		 
		String expectedResp = "{\r\n"
				+ "    \"data\": [\r\n"
				+ "        {\r\n"
				+ "            \"dishId\": 1,\r\n"
				+ "            \"dishName\": \"Carrot Salad\",\r\n"
				+ "            \"recipe\": \"Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts\",\r\n"
				+ "            \"foodType\": \"Veg\",\r\n"
				+ "            \"numOfServings\": 4\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"message\": \"Successfully Retrieved data!\",\r\n"
				+ "    \"status\": 200\r\n"
				+ "}";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<FoodInfo> request = new HttpEntity<FoodInfo>(recipeOne,headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9099/food/fetchRecipeByParams", request, String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expectedResp, response.getBody(), false);
	}
	
	@Test
	@Order(5)
	public void deleteRecipeTest() throws JSONException {
		
		 FoodInfo recipeOne = new FoodInfo("Carrot Salad",1, "Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts",
					"Veg",0);
		 
		String expectedResp = "{\r\n"
				+ "    \"data\": {\r\n"
				+ "        \"dishId\": 1,\r\n"
				+ "        \"dishName\": \"Carrot Salad\",\r\n"
				+ "        \"recipe\": \"Carrots, almonds, raisins and spring onions work together to create a gorgeous desserts\",\r\n"
				+ "        \"foodType\": \"Veg\",\r\n"
				+ "        \"numOfServings\": 4\r\n"
				+ "    },\r\n"
				+ "    \"message\": \"Deleted!\",\r\n"
				+ "    \"status\": 200\r\n"
				+ "}";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<FoodInfo> request = new HttpEntity<FoodInfo>(recipeOne,headers);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9099/food/deleteRecipe/1", HttpMethod.DELETE, request, String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expectedResp, response.getBody(), false);
	}
}
