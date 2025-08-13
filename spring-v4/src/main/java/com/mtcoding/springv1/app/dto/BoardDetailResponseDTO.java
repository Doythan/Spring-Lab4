package com.mtcoding.springv1.app.dto;

import com.mtcoding.springv1.domain.board.Board;
import com.mtcoding.springv1.domain.reply.Reply;
import lombok.Data;

import java.util.List;

@Data
public class BoardDetailResponseDTO {
    private int id;
    private String title;
    private String content;

    private int boardUserId;
    private String boardUsername;

    // 댓글들
    List<ReplyDTO> replies;


    public BoardDetailResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardUserId = board.getUser().getId();
        this.boardUsername = board.getUser().getUsername();
        this.replies = board.getReplies().stream()
                .map(reply -> new ReplyDTO(reply)).toList();
    }

    @Data
    public class ReplyDTO {
        private int replyId;
        private String comment;

        private int replyUserId;
        private String replyUsername;

        public ReplyDTO(Reply reply) {
            this.replyId = reply.getId();
            this.comment = reply.getComment();
            this.replyUserId = reply.getUser().getId();
            this.replyUsername = reply.getUser().getUsername();
        }
    }
}