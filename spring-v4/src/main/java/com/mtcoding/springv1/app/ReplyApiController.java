package com.mtcoding.springv1.app;

import com.mtcoding.springv1.app.dto.ReplySaveRequestDTO;
import com.mtcoding.springv1.domain.reply.ReplyService;
import com.mtcoding.springv1.domain.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReplyApiController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/save")
    public String save(ReplySaveRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 하세요");

        replyService.댓글쓰기(reqDTO, sessionUser);

        return "redirect:/board/" + reqDTO.getBoardId();
    }

    @PostMapping("/reply/{id}/delete")
    public String deleteById(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 하세요");

        Integer boardId = replyService.댓글삭제(id, sessionUser);
        return "redirect:/board/" + boardId;
    }

}
