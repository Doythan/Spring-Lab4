package com.mtcoding.springv1.app;

import com.mtcoding.springv1.app.dto.BoardResponseDTO;
import com.mtcoding.springv1.app.dto.BoardSaveRequestDTO;
import com.mtcoding.springv1.core.handler.ex.Exception401;
import com.mtcoding.springv1.core.util.Resp;
import com.mtcoding.springv1.domain.board.BoardService;
import com.mtcoding.springv1.domain.user.User;
import com.mtcoding.springv1.app.dto.BoardDetailResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller // DS가 endPoint로 찾을 수 있고, 파일을 찾아서 응답
public class BoardApiController {

    private final BoardService boardService;
    private final HttpSession session;

    // select * from board_tb where title = '스프링';
    // localhost:8080/board?title=스프링
    // select * from board_tb where id = 1;
    // localhost:8080/board/1 -> PathValue
    @PutMapping("/{id}")
    public Resp updateById(@PathVariable("id") int id, @RequestBody BoardSaveRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) throw new Exception401("로그인 하세요");

        var respDTO = boardService.게시글수정(id, reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    @DeleteMapping("/{id}")
    public Resp deleteById(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) throw new Exception401("로그인 하세요");

        boardService.게시글삭제(id, sessionUser);
        return null;
    }

    @PostMapping
    public Resp save(@RequestBody BoardSaveRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) throw new Exception401("로그인 하세요");

        var respDTO = boardService.게시글쓰기(reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    @GetMapping
    public Resp list() {
        var respDTO = boardService.게시글목록();
        return Resp.ok(respDTO); // text/html
    }

    @GetMapping("/{id}")
    public Resp detail(@PathVariable("id") int id) {

        var respDTO = boardService.게시글상세(id);

        return Resp.ok(respDTO);
    }
}
