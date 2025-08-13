package com.mtcoding.springv1.core.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    // ResponseEntity 형태로 바로 리턴 (성공)
    public static <B> Resp<B> ok(B body) {
        return new Resp<>(200, "성공", body);
    }

    // ResponseEntity 형태로 바로 리턴 (실패)
    public static Resp<?> fail(HttpStatus status, String msg) {
        return new Resp<>(status.value(), msg, null);
    }

    public static Resp<?> fail(Integer status, String msg) { // 스프링 도움 안받을때!!
        Resp<?> resp = new Resp<>(status, msg, null);
        return resp;
    }
}