package com.mtcoding.springv1.app.dto;

import com.mtcoding.springv1.domain.board.Board;
import lombok.Data;

@Data
public class BoardSaveOrUpdateResponseDTO {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;

    public BoardSaveOrUpdateResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userId = board.getUser().getId();
    }
}