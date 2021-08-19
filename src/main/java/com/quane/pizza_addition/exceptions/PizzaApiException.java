package com.quane.pizza_addition.exceptions;

public class PizzaApiException extends RuntimeException{

    public PizzaApiException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public PizzaApiException(String exMessage) {
        super(exMessage);
    }

}
