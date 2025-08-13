package com.mtcoding.springv1.app;

import com.mtcoding.springv1.app.dto.BoardResponseDTO;
import com.mtcoding.springv1.app.dto.BoardSaveRequestDTO;
import com.mtcoding.springv1.domain.board.BoardService;
import com.mtcoding.springv1.domain.user.User;
import com.mtcoding.springv1.app.dto.BoardDetailResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @PostMapping("/board/{id}/update")
    public String updateById(@PathVariable("id") int id, BoardSaveRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) throw new RuntimeException("로그인 하세요");

        boardService.게시글수정(id, reqDTO, sessionUser);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String deleteById(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) throw new RuntimeException("로그인 하세요");

        boardService.게시글삭제(id, sessionUser);
        return "redirect:/board";
    }

    @PostMapping("/board/save")
    public String save(BoardSaveRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) throw new RuntimeException("로그인 하세요");

        boardService.게시글쓰기(reqDTO, sessionUser);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String list(HttpServletRequest request) {
        List<BoardResponseDTO> respDTO = boardService.게시글목록();

        request.setAttribute("models", respDTO);

        return "board/list"; // text/html
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) throw new RuntimeException("로그인 하세요");

        BoardDetailResponseDTO respDTO = boardService.게시글상세(id);

        request.setAttribute("model", respDTO);

        return "board/update-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest request) {

        BoardDetailResponseDTO respDTO = boardService.게시글상세(id);

        request.setAttribute("model", respDTO);

        return "board/detail";
    }
}
