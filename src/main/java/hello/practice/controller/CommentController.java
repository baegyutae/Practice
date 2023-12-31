package hello.practice.controller;

import hello.practice.dto.CommentResponseDto;
import hello.practice.dto.CreateCommentRequestDto;
import hello.practice.dto.UpdateCommentRequestDto;
import hello.practice.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/post/{postId}")
    public List<CommentResponseDto> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentsByPostId(postId);
    }

    @GetMapping("/{id}")
    public CommentResponseDto getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody UpdateCommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
