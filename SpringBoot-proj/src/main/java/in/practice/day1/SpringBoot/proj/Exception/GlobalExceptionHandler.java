package in.practice.day1.SpringBoot.proj.Exception;

import in.practice.day1.SpringBoot.proj.util.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorDetails info = new ErrorDetails();
        info.setCode("ERR-404");
        info.setMessage(ex.getMessage());
        info.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorDetails info = new ErrorDetails();
        info.setCode("ERR-VALIDATION");
        info.setMessage(message);
        info.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }

}
