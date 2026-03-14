package org.nuoyue.exception;


import lombok.extern.slf4j.Slf4j;
import org.nuoyue.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("An error occurred: ", e);
        return Result.error("An unexpected error occurred. Please try again later.");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("Duplicate key error: ", e);
        String message = e.getMessage();
        int msgIndex = message.indexOf("Duplicate entry");
        String errorMsg = message.substring(msgIndex);
        String[] parts = errorMsg.split(" ");
        String duplicateValue = parts[2].replace("'", "");
        return Result.error("The value '" + parts[2] +
                "' already exists. Please use a different value.");

    }

}
