package com.example.bakingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Receipe implements Serializable {

    private int id;
    private String name;
    private String image;
    private List<Ingredients> ingredients;
    private List<Steps> steps;

    public Receipe(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("image") String image,
                   @JsonProperty("ingredients") List<Ingredients> ingredients,
                   @JsonProperty("steps") List<Steps> steps) {
            this.id = id;
            this.name = name;
            this.image  = image;
            this.ingredients = ingredients;
            this.steps = steps;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }
}
