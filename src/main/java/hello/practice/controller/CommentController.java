package hello.practice.controller;

import hello.practice.dto.CommentResponseDto;
import hello.practice.dto.CreateCommentRequestDto;
import hello.practice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto createComment(@RequestBody CreateCommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }
}
