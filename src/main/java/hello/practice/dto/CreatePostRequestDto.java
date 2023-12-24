package hello.practice.dto;

import jakarta.validation.constraints.Size;

public record CreatePostRequestDto(
    @Size(max = 500, message = "제목은 500자 이내여야 합니다.")
    String title,

    @Size(max = 5000, message = "내용은 5000자 이내여야 합니다.")
    String content,

    String nickname) {
}