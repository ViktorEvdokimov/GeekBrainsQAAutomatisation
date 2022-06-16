package ru.education.apiTest.mealplanner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.education.apiTest.BaseTest;
import ru.education.apiTest.entities.mealplanner.DeleteShoppingListResponce;
import ru.education.apiTest.entities.mealplanner.GetShoppingListResponse;
import ru.education.apiTest.entities.mealplanner.PostShoppingListRequest;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Long> ids = response.as(GetShoppingListResponse.class).aisles.stream()
                .flatMap(it -> it.items.stream())
                .map(it-> it.id)
                .collect(Collectors.toList());

        for (int i = 0; i < ids.size(); i++) {
                Long id = ids.get(i);
                given()
                        .queryParam("apiKey", apiKey)
                        .queryParam("hash", hash)
                        .when()
                        .delete(url + "/items/" + id);
        }

    }

    @Test
    void checkCauliflower() throws JsonProcessingException {
        PostShoppingListRequest postShoppingListRequest = new PostShoppingListRequest("1 goodbite chicken patty-2 pack", null, true);
        ObjectMapper om = new ObjectMapper();
        om.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        String jsonString = om.writeValueAsString(postShoppingListRequest);
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .contentType("application/json")
                .body(jsonString)
                .when()
                .post(url + "/items");

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.as(GetShoppingListResponse.Item.class).name, is("goodbite chicken patty-2 pack"));
        assertThat(response.as(GetShoppingListResponse.Item.class).cost, is(506.15F));

        Long id = response.as(GetShoppingListResponse.Item.class).id;

        response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .when()
                .get(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().as(GetShoppingListResponse.class).aisles.stream()
                .flatMap(it -> it.items.stream())
                .filter(it -> it.id.equals(id))
                .count(), is(1L));

        response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .when()
                .delete(url + "/items/" + id);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.as(DeleteShoppingListResponce.class).status, is ("success"));

        response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .when()
                .get(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().as(GetShoppingListResponse.class).aisles.stream()
                .flatMap(it -> it.items.stream())
                .filter(it -> it.id.equals(id))
                .count(), is(0L));
    }
}
