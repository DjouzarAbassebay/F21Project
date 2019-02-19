package com.company;

public class InvalidItemNameException extends Exception {



        public InvalidItemNameException(String name){
            super("The name should not contain any number: " + name);
        }
    }



