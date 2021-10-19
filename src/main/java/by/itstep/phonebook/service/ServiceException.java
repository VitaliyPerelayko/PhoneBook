package by.itstep.phonebook.service;

public class ServiceException extends RuntimeException{

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Exception ex){
        super(message, ex);
    }

    public ServiceException(String message, Object... obj){
        this(String.format(message,obj));
    }
}
