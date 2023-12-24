package hello.practice.controller;

import hello.practice.dto.LoginRequestDto;
import hello.practice.dto.SignUpRequestDto;
import hello.practice.service.JwtService;
import hello.practice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequestDto requestDto) {
        userService.signup(requestDto);
        return "회원가입 완료";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest httpRequest) {
        boolean loginSuccess = userService.login(requestDto);
        if (loginSuccess) {
            String token = jwtService.generateToken(requestDto.username());
            ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .path(httpRequest.getContextPath())
                .build();

            return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Login successful");
        } else {
            return ResponseEntity.badRequest().body("닉네임 또는 패스워드를 확인해주세요.");
        }
    }
}