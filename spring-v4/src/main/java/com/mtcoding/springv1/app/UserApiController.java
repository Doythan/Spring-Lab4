package com.mtcoding.springv1.app;

import com.mtcoding.springv1.app.dto.JoinRequestDTO;
import com.mtcoding.springv1.app.dto.LoginRequestDTO;
import com.mtcoding.springv1.core.util.Resp;
import com.mtcoding.springv1.domain.user.User;
import com.mtcoding.springv1.domain.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session; // IoC에 등록되어 있음.

    @GetMapping("/logout")
    public Resp logout() {
        session.invalidate();
        return Resp.ok(null);  // 오브젝트를 리턴하면 json으로 컨버팅해서 리턴해줌
    }

    @PostMapping("/join")
    public Resp join(@RequestBody JoinRequestDTO reqDTO) {
        var respDTO = userService.회원가입(reqDTO);
        return Resp.ok(respDTO);
    }

    @PostMapping("/login") // 조회인데, Post는 로그인 밖에 없다. 예외 (URL에 쿼리스트링으로 정보 전달을 안하려고)
    public Resp login(@RequestBody LoginRequestDTO reqDTO) {
        User sessionUser = userService.로그인(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Resp.ok(null);
    }
}







