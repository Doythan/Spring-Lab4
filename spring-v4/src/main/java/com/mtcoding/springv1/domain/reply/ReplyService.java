package com.mtcoding.springv1.domain.reply;

import com.mtcoding.springv1.app.dto.ReplySaveRequestDTO;
import com.mtcoding.springv1.domain.board.Board;
import com.mtcoding.springv1.domain.board.BoardJpaRepository;
import com.mtcoding.springv1.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJpaRepository replyJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @Transactional
    public void 댓글쓰기(ReplySaveRequestDTO reqDTO, User sessionUser) {
        Board findBoard = boardJpaRepository.findById(reqDTO.getBoardId());
        //Board myBoard = new Board(reqDTO.getBoardId(), null, null, null);
        //User myUser = new User(sessionUser.getId(), null, null, null);

        if (findBoard == null) throw new RuntimeException("없는 게시글에 글을 쓸 수 없어요 404");

        Reply reply = new Reply(null, reqDTO.getComment(), sessionUser, findBoard);
        replyJpaRepository.save(reply);
    }

    @Transactional
    public Integer 댓글삭제(Integer id, User sessionUser) {
        Reply findReply = replyJpaRepository.findById(id);

        if (findReply == null) throw new RuntimeException("없는 댓글을 삭제할 수 없어요 404");

        if (!findReply.getUser().getId().equals(sessionUser.getId())) {
            throw new RuntimeException("권한이 없습니다. 403");
        }

        int boardId = findReply.getBoard().getId();

        replyJpaRepository.deleteById(id);

        return boardId;
    }
}
