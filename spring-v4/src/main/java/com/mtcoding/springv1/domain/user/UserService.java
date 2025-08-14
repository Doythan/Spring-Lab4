package com.mtcoding.springv1.domain.user;

import com.mtcoding.springv1.app.dto.JoinRequestDTO;
import com.mtcoding.springv1.app.dto.LoginRequestDTO;
import com.mtcoding.springv1.app.dto.UserResponseDTO;
import com.mtcoding.springv1.core.handler.ex.Exception400;
import com.mtcoding.springv1.core.handler.ex.Exception401;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public UserResponseDTO 회원가입(JoinRequestDTO reqDTO) {
        // 1. username 중복 확인
        User findUser = userJpaRepository.findByUsername(reqDTO.getUsername());
        if (findUser != null) {
            throw new Exception400("username이 존재합니다 : " + reqDTO.getUsername());
        }
        // 2. insert
        User user = new User(null, reqDTO.getUsername(), reqDTO.getPassword(), reqDTO.getEmail());
        User savedUser = userJpaRepository.save(user);


        return new UserResponseDTO(savedUser);
    }

    public User 로그인(LoginRequestDTO reqDTO) {
        User findUser = userJpaRepository.findByUsername(reqDTO.getUsername());

        if (findUser == null) throw new Exception401("username이 틀렸습니다");

        if (!findUser.getPassword().equals(reqDTO.getPassword())) throw new Exception401("password가 틀렸습니다");

        return findUser;
    }
}
