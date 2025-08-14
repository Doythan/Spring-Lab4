package com.mtcoding.springv1.app.dto;

import com.mtcoding.springv1.domain.reply.Reply;
import lombok.Data;

@Data
public class ReplySaveResponseDTO {
    private int id;
    private String comment;
    private int userId;
    private int boardId;

    public ReplySaveResponseDTO(Reply reply) {
        this.id = reply.getId();
        this.comment = reply.getComment();
        this.userId = reply.getUser().getId();
        this.boardId = reply.getBoard().getId();
    }
}