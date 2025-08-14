package com.mtcoding.springv1.core.handler;

import com.mtcoding.springv1.core.handler.ex.*;
import com.mtcoding.springv1.core.util.Resp;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity badRequest(Exception400 e, HttpServletResponse response) {
        //response.setStatus(400);
        return new ResponseEntity(Resp.fail(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity unAuthorized(Exception401 e) {
        return new ResponseEntity(Resp.fail(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity forbidden(Exception403 e) {
        return new ResponseEntity(Resp.fail(403, e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity notFound(Exception404 e) {
        return new ResponseEntity(Resp.fail(404, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity badRequest(Exception500 e) {
        //response.setStatus(400);
        return new ResponseEntity(Resp.fail(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity unKnown(Exception e) {
        System.out.println("파일로 기록 : " + e.getMessage());
        return new ResponseEntity(Resp.fail(500, "관리자에게 문의하세요"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}