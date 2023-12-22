package hello.practice.service;

import hello.practice.dto.LoginRequestDto;
import hello.practice.dto.SignUpRequestDto;
import hello.practice.entity.User;
import hello.practice.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignUpRequestDto requestDto) {
        User user = User.builder()
            .username(requestDto.username())
            .password(requestDto.password())
            .email(requestDto.email())
            .build();
        userRepository.save(user);
    }

    private void validateUsername(String username) {
        if (username.length() < 3 || !username.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public boolean login(LoginRequestDto requestDto) {
        Optional<User> userOptional = userRepository.findByUsername(requestDto.username());
        if (userOptional.isPresent() && userOptional.get().getPassword()
            .equals(requestDto.password())) {
            return true; // 로그인 성공
        }
        return false; // 로그인 실패
    }
}