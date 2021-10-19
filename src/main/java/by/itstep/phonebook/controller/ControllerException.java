package by.itstep.phonebook.controller;

public class ControllerException extends RuntimeException {

    public ControllerException(String message){
        super(message);
    }

    public ControllerException(String message, Object... obj){
        this(String.format(message,obj));
    }
}
