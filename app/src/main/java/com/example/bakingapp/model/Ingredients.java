package com.example.bakingapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Ingredients implements Serializable {
   private int quantity;
   private String measure;
   private String ingredient;

   public Ingredients(@JsonProperty("quantity") int quantity,
                      @JsonProperty("measure") String measure,
                      @JsonProperty("ingredient") String ingredient) {
            this.ingredient = ingredient;
            this.measure = measure;
            this.quantity =quantity;
   }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
