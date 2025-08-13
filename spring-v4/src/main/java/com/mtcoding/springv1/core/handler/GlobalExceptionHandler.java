package com.mtcoding.springv1.core.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody String back(RuntimeException e) {
        String html = """
                <script>
                    alert('#{msg}');
                    history.back();
                </script>
                """.replace("#{msg}", e.getMessage());
        return html;
    }
}
