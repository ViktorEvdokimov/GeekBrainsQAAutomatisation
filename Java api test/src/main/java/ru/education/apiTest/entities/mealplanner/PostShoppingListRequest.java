package ru.education.apiTest.entities.mealplanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class PostShoppingListRequest {

    @JsonProperty("item")
    public String item;
    @JsonProperty("aisle")
    public String aisle;
    @JsonProperty("parse")
    public Boolean parse;

    public PostShoppingListRequest(String item, String aisle, Boolean parse) {
        this.item = item;
        this.aisle = aisle;
        this.parse = parse;
    }
}
