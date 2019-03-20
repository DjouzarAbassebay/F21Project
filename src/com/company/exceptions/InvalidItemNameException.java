package com.company.exceptions;

public class InvalidItemNameException extends Exception {

        // exception to validate the name of an item
        public InvalidItemNameException(String name){
            super("The name should not contain any number: " + name);
    }
}



