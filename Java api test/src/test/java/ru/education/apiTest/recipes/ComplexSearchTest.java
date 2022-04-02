package ru.education.apiTest.recipes;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ru.education.apiTest.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ComplexSearchTest extends BaseTest {

    private static String url = baseUrl + "/recipes/complexSearch";

    @Test
    void checkPageSize(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", "false")
                .when()
                .get(url);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("number"), is(10));
    }

    @Test
    void searchByColories(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("minCalories", "588")
                .queryParam("maxCalories", "589")
                .when()
                .get(url);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("results[0].id"), is(729366));
    }

    @Test
    void searchByColoriesCheckSort(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("minCalories", "435")
                .queryParam("maxCalories", "880")
                .queryParam("sort", "calories")
                .queryParam("sortDirection", "asc")
                .when()
                .get(url);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("results[0].id"), is(982374));
    }

    @Test
    void searchByCarbs(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("minCarbs", "27.7862")
                .queryParam("maxCarbs", "27.7863")
                .when()
                .get(url);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("results[0].id"), is(645884));
    }

    @Test
    void searchByCarbsCheckSort(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("minCarbs", "55")
                .queryParam("maxCarbs", "70")
                .queryParam("sort", "Carbohydrates")
                .when()
                .get(url);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("results[0].id"), is(632737));
    }
}
