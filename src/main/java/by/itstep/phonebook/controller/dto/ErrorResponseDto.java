package by.itstep.phonebook.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {

    private String message;

    private String exceptionMessage;

    private String exceptionStackTrace;

    @JsonIgnore
    private Throwable exception;

    public ErrorResponseDto(String message, Throwable exception) {
        this.message = message;
        this.exception = exception;
    }

    public ErrorResponseDto(String message) {
        this.message = message;
    }

    public ErrorResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public String getExceptionMessage() {
        return exception.getMessage();
    }

    public String getExceptionStackTrace() {
        return Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).reduce("at\n", (line, nextLie) -> line.concat("\n").concat(nextLie));
    }
}
