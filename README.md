**Spring Boot Project for managing "Favorite Recipes"**

- Clone this repository
- Make sure you are using JDK 1.8 and Maven
- You can build the project and run the tests by running mvn clean package

This is a Spring boot rest application that allows user to manage their favorite recipes. It uses an in-memory database (H2) to store the data. You can call some REST endpoints defined in com.food.recipes.Controller on port 9099.

**Fetch all recipes :-**

    GET /food/recipes

    Accept: application/json

    Content-Type: application/json

    Parameters :- No parameters

    RESPONSE: HTTP 200 (OK)

**Fetch Recipe by its Id :-**

    GET /food/recipes/{id}

    Accept: application/json

    Content-Type: application/json

    Parameters :- {id} required

    RESPONSE: HTTP 200 (OK)

**Fetch Recipe by parameters:-**

    POST /food/fetchRecipeByParams

    Accept: application/json

    Content-Type: application/json

    Parameters :-

    Input Request :-

    {

    "dishId": 0,

    "dishName": "string",

    "foodType": "string",

    "numOfServings": 0,

    "recipe": "string"

    }

    RESPONSE: HTTP 200 (OK)

**Add /Update Recipe :-**

    POST /food/addRecipe

    Accept: application/json

    Content-Type: application/json

    Parameters :-

    Input Request :-

    {

    "dishId": 0,

    "dishName": "string",

    "foodType": "string",

    "numOfServings": 0,

    "recipe": "string"

    }

    RESPONSE: HTTP 200 (OK)

**Delete Recipe :-**

    POST /food/deleteRecipe/{dishId}

    Accept: application/json

    Content-Type: application/json

    Parameters :- {dishId} required

    RESPONSE: HTTP 200 (OK)
    

**To view Swagger 2 API docs**

 Run the server and browse to [http://localhost:9099/swagger-ui/index.html#/](http://localhost:9099/swagger-ui/index.html#/)

**To view your H2 in-memory database**

The 'test' profile runs on H2 in-memory database. To view and query the database you can browse to [http://localhost:9099/h2-console/](http://localhost:9099/h2-console/).

**Implemented Junit and Mockito to perform Unit testing.**

Included Unit tests and Integration tests for all endpoints.

