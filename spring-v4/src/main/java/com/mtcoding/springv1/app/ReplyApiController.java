package com.mtcoding.springv1.app;

import com.mtcoding.springv1.app.dto.ReplySaveRequestDTO;
import com.mtcoding.springv1.core.handler.ex.Exception401;
import com.mtcoding.springv1.core.util.Resp;
import com.mtcoding.springv1.domain.reply.ReplyService;
import com.mtcoding.springv1.domain.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/replies")
@RequiredArgsConstructor
@Controller
public class ReplyApiController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping
    public Resp save(@RequestBody ReplySaveRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new Exception401("로그인 하세요");

        var respDTO = replyService.댓글쓰기(reqDTO, sessionUser);

        return Resp.ok(respDTO);
    }

    @DeleteMapping("/{id}")
    public Resp deleteById(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new Exception401("로그인 하세요");

        replyService.댓글삭제(id, sessionUser);
        return Resp.ok(null);
    }

}
