package com.company.exceptions;

public class InvalidItemIdException extends Exception{

    public InvalidItemIdException(String name){
        super("This id is not validate : " + name);
    }

}
