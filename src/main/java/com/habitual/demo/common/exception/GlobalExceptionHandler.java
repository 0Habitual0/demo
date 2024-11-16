package com.habitual.demo.common.exception;

import com.habitual.demo.common.service.ExceptionHandlerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @ExceptionHandler(TokenValidationException.class)
    public void handleTokenValidationException(HttpServletResponse response, TokenValidationException ex, WebRequest request) throws IOException {
        exceptionHandlerService.handleTokenValidationException(response, ex);
    }

}
