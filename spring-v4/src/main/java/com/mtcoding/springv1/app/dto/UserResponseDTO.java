package com.mtcoding.springv1.app.dto;

import com.mtcoding.springv1.domain.user.User;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}