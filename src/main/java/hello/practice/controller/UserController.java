package hello.practice.controller;

import hello.practice.dto.CreateUserRequestDto;
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
    public void signup(@RequestBody CreateUserRequestDto requestDto) {
        userService.signup(requestDto);
    }

}
