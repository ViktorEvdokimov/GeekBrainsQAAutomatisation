package ru.education.apiTest.mealplanner;

import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.education.apiTest.BaseTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingList  extends BaseTest{

    protected static String userName = "my";
    protected static String spoonacularPassword = "tonicon26tomyam";
    protected static String hash = "f702d8e739e58b34e71ec1528a7d75d5472b924a";

    protected static String baseUrlMealPlaner = baseUrl + "/mealplanner/" + userName;

    private final String url = baseUrlMealPlaner + "/shopping-list";

    @AfterEach
    void tearDown(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .when()
                .get(url);
        List<List<Map>> itemLists = response.getBody().jsonPath().getList("aisles.items");

        for (int i = 0; i < itemLists.size(); i++) {
            List<Map> items = itemLists.get(i);
            for (int j = 0; j < items.size(); j++) {
                String id = items.get(j).get("id").toString();
                given()
                        .queryParam("apiKey", apiKey)
                        .queryParam("hash", hash)
                        .when()
                        .delete(url + "/items/" + id);
            }
        }

    }

    @Test
    void checkCauliflower(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .contentType("application/json")
                .body("{\"item\":\"1 goodbite chicken patty-2 pack\",\"aisle\":\"Baking\",\"parse\": true}")
                .when()
                .post(url + "/items");

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("name"), is ("goodbite chicken patty-2 pack"));
        assertThat(response.getBody().jsonPath().get("cost"), is(506.15F));

        String id = response.getBody().jsonPath().get("id").toString();

        response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .when()
                .get(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().print(), containsString(id));

        response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .when()
                .delete(url + "/items/" + id);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("status"), is ("success"));

        response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .when()
                .get(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().print(), !response.getBody().print().contains(id));
    }
}
