package by.itstep.phonebook.configuration;

import by.itstep.phonebook.controller.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
    private static final String NEXT_LINE = "\n";
    private static final String EMPTY = "";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = exception.getBindingResult().getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage().concat(NEXT_LINE))
                .reduce(EMPTY, String::concat);
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(errorMessage);
        LOGGER.error(errorMessage, exception);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    // TODO если ошибка упала у админа отбвавть ему респонс со стектрейсом наверно делает тоже самое, надо посмотреть
    //handleExceptionInternal(exception, errorResponseDTO, new HttpHeaders(), errorResponseDTO.getHttpStatus(), request);
    protected ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException exception, WebRequest request) {
        LOGGER.error(exception.getMessage(), exception);
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
