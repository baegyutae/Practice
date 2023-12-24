package hello.practice.dto;

import java.time.LocalDateTime;

public record PostResponseDto(
    Long id,
    String title,
    String nickname,
    String content,
    LocalDateTime careatedAt) {

}
