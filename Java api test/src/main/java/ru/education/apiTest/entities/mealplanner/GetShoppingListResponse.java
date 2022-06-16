package ru.education.apiTest.entities.mealplanner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetShoppingListResponse {

    @JsonProperty("aisles")
    public List<Aisle> aisles;
    @JsonProperty("cost")
    public Float cost;
    @JsonProperty("startDate")
    public Long startDate;
    @JsonProperty("endDate")
    public Long endDate;

    public static class Aisle{
        @JsonProperty("aisle")
        public String aisle;
        @JsonProperty("items")
        public List<Item> items;
    }

    public static class Item{
        @JsonProperty("id")
        public Long id;
        @JsonProperty("name")
        public String name;
        @JsonProperty("measures")
        public Measures measures;
        @JsonProperty("usages")
        public List<String> usages;
        @JsonProperty("usageRecipeIds")
        public List<String> usageRecipeIds;
        @JsonProperty("pantryItem")
        public Boolean pantryItem;
        @JsonProperty("aisle")
        public String aisle;
        @JsonProperty("cost")
        public Float cost;
        @JsonProperty("ingredientId")
        public Long ingredientId;
    }

    public static class Measures{
        @JsonProperty("original")
        public Measure original;
        @JsonProperty("metric")
        public Measure metric;
        @JsonProperty("us")
        public Measure us;
    }

    public static class Measure{
        @JsonProperty("amount")
        public Float amount;
        @JsonProperty("unit")
        public String unit;
    }
}
