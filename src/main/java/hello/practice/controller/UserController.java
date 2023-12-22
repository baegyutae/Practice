package hello.practice.controller;

import hello.practice.dto.LoginRequestDto;
import hello.practice.dto.SignUpRequestDto;
import hello.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequestDto requestDto) {
        userService.signup(requestDto);
        return "회원가입 완료";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        boolean loginSuccess = userService.login(requestDto);
        if (loginSuccess) {
            return "로그인 성공";
        } else {
            return "로그인 실패";
        }
    }
}