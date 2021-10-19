package by.itstep.phonebook.service.validation;

public class ServiceValidationException extends RuntimeException {

    public ServiceValidationException(String message){
        super(message);
    }

    public ServiceValidationException(String message, Exception ex){
        super(message, ex);
    }

    public ServiceValidationException(String message, Object obj){
        this(String.format(message,obj));
    }
}
