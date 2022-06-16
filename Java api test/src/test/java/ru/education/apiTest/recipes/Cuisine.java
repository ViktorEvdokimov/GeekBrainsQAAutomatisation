package ru.education.apiTest.recipes;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ru.education.apiTest.BaseTest;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Cuisine extends BaseTest {

    private static String url = baseUrl + "/recipes/cuisine";

    @Test
    void checkCauliflower(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", "false")
                .formParam("title", "Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .when()
                .post(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("cuisine"), is("Chinese"));
        assertThat(response.getBody().jsonPath().get("cuisines"), is (Arrays.asList(new String[]{"Chinese", "Asian"})));
        assertThat(response.getBody().jsonPath().get("confidence"), is(0.85F));
    }

    @Test
    void checkHomemade(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", "false")
                .formParam("title", "Homemade Garlic and Basil French Fries")
                .when()
                .post(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("cuisine"), is("American"));
        assertThat(response.getBody().jsonPath().get("cuisines"), is (Arrays.asList(new String[]{"American"})));
        assertThat(response.getBody().jsonPath().get("confidence"), is(0.85F));
    }

    @Test
    void checkBerryBanana(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", "false")
                .formParam("title", "Berry Banana Breakfast Smoothie")
                .when()
                .post(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("cuisine"), is("Mediterranean"));
        assertThat(response.getBody().jsonPath().get("cuisines"), is (Arrays.asList(new String[]{"Mediterranean", "European", "Italian"})));
        assertThat(response.getBody().jsonPath().get("confidence"), is(0F));
    }

    @Test
    void checkGarlickyKale(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", "false")
                .formParam("title", "Garlicky Kale")
                .when()
                .post(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("cuisine"), is("Mediterranean"));
        assertThat(response.getBody().jsonPath().get("cuisines"), is (Arrays.asList(new String[]{"Mediterranean", "European", "Italian"})));
        assertThat(response.getBody().jsonPath().get("confidence"), is(0F));
    }

    @Test
    void checkAfricanChickenPeanutStew(){
        Response response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", "false")
                .formParam("title", "African Chicken Peanut Stew")
                .when()
                .post(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("cuisine"), is("African"));
        assertThat(response.getBody().jsonPath().get("cuisines"), is (Arrays.asList(new String[]{"African"})));
        assertThat(response.getBody().jsonPath().get("confidence"), is(0.85F));
    }
}
