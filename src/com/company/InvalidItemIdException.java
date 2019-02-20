package com.company;

public class InvalidItemIdException extends Exception{

    public InvalidItemIdException(String name){
        super("This id is not validate : " + name);
    }

}
