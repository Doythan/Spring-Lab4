package com.mtcoding.springv1.domain.board;

import com.mtcoding.springv1.app.dto.BoardDetailResponseDTO;
import com.mtcoding.springv1.app.dto.BoardResponseDTO;
import com.mtcoding.springv1.app.dto.BoardSaveOrUpdateResponseDTO;
import com.mtcoding.springv1.app.dto.BoardSaveRequestDTO;
import com.mtcoding.springv1.core.handler.ex.Exception403;
import com.mtcoding.springv1.core.handler.ex.Exception404;
import com.mtcoding.springv1.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// 비지니스로직, 트랜잭션관리, 응답DTO 생성
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;

    public List<BoardResponseDTO> 게시글목록() {
        List<Board> boardList = boardJpaRepository.findAll();

        List<BoardResponseDTO> respDTO = new ArrayList<>();
        for (Board board : boardList) {
            respDTO.add(new BoardResponseDTO(board));
        }
        return respDTO;
    }


    public BoardDetailResponseDTO 게시글상세(int id) {
        Board board = boardJpaRepository.findByIdJoinUserAndReplies(id); // board

        BoardDetailResponseDTO respDTO = new BoardDetailResponseDTO(board);

        return respDTO;
    }

    @Transactional
    public BoardSaveOrUpdateResponseDTO 게시글쓰기(BoardSaveRequestDTO boardSaveRequestDTO, User sessionUser) {
        Board board = new Board(null, boardSaveRequestDTO.getTitle(), boardSaveRequestDTO.getContent(), sessionUser);
        Board savedBoard = boardJpaRepository.save(board);
        return new BoardSaveOrUpdateResponseDTO(savedBoard);
    }

    @Transactional
    public void 게시글삭제(int id, User sessionUser) {
        Board findBoard = boardJpaRepository.findById(id);

        if (findBoard == null) {
            throw new Exception404("게시글을 찾을 수 없음");
        }

        if (findBoard.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("권한 없음");
        }

        boardJpaRepository.deleteById(id);
    }

    @Transactional
    public BoardSaveOrUpdateResponseDTO 게시글수정(int id, BoardSaveRequestDTO boardSaveRequestDTO, User sessionUser) {
        Board findBoard = boardJpaRepository.findById(id);

        if (findBoard == null) {
            throw new RuntimeException("게시글을 찾을 수 없음 404");
        }

        if (findBoard.getUser().getId() != sessionUser.getId()) {
            throw new RuntimeException("권한 없음 403");
        }

        findBoard.update(boardSaveRequestDTO.getTitle(), boardSaveRequestDTO.getContent()); // 상태변경

        return new BoardSaveOrUpdateResponseDTO(findBoard);
    } // 트랜잭션 종료시에 flush가 자동으로 일어나면서 더티체킹이 된다.
}