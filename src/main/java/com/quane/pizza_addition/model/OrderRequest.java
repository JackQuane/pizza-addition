package com.quane.pizza_addition.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(onConstructor=@__(@JsonCreator))
@NoArgsConstructor
public class OrderRequest {

    @JsonProperty("Crust")
    private String crust;
    @JsonProperty("Flavor")
    private String flavor;
    @JsonProperty("Size")
    private String size;
    @JsonProperty("Table_No")
    private int tableNum;

}
