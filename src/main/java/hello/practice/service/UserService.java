package hello.practice.service;

import hello.practice.dto.CreateUserRequestDto;
import hello.practice.entity.User;
import hello.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(CreateUserRequestDto requestDto) {
        User user = User.builder()
            .username(requestDto.username())
            .password(requestDto.password())
            .email(requestDto.email())
            .build();
        userRepository.save(user);
    }

}
