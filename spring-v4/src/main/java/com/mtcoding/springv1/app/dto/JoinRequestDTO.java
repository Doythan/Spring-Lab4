package com.mtcoding.springv1.app.dto;

import lombok.Data;

@Data
public class JoinRequestDTO {
    private String username;
    private String password;
    private String email;
}
