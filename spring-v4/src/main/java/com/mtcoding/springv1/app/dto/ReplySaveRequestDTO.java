package com.mtcoding.springv1.app.dto;

import lombok.Data;

@Data
public class ReplySaveRequestDTO {
    private Integer boardId;
    private String comment;
}
