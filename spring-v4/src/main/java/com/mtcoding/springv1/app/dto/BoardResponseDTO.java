package com.mtcoding.springv1.app.dto;

import com.mtcoding.springv1.domain.board.Board;
import lombok.Data;

@Data
public class BoardResponseDTO {
    private int id;
    private String title;

    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }
}