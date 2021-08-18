package com.quane.pizza_addition.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor(onConstructor=@__(@JsonCreator))
@NoArgsConstructor
public class OrderResponse {

    @JsonProperty("Crust")
    private String crust;
    @JsonProperty("Flavor")
    private String flavor;
    @JsonProperty("Order_ID")
    private int orderId;
    @JsonProperty("Size")
    private String size;
    @JsonProperty("Table_No")
    private int tableNum;
    @JsonProperty("Timestamp")
    private Timestamp timestamp;

}
