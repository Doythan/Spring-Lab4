package com.mtcoding.springv1.domain.reply;

import com.mtcoding.springv1.domain.board.Board;
import com.mtcoding.springv1.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "reply_tb")
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment; // 댓글 내용

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public Reply(Integer id, String comment, User user, Board board) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.board = board;
    }
}
