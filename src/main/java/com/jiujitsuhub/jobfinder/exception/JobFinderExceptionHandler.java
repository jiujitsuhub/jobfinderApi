package com.jiujitsuhub.jobfinder.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class JobFinderExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<String> handleJobNotFoundException(Exception e) {
        logger.warn(e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "We are sorry to say that something went wrong")
    public ResponseEntity<String> handleAnyOtherException(Exception e) {
        logger.error(e);
        e.printStackTrace();
        return ResponseEntity
                .internalServerError()
                .body("We are sorry to say that something went wrong");
    }

}
