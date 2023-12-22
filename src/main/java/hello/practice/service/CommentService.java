package hello.practice.service;

import hello.practice.dto.CommentResponseDto;
import hello.practice.dto.CreateCommentRequestDto;
import hello.practice.dto.UpdateCommentRequestDto;
import hello.practice.entity.Comment;
import hello.practice.repository.CommentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CreateCommentRequestDto request) {
        Comment comment = Comment.builder()
            .postId(request.postId())
            .content(request.content())
            .build();
        comment = commentRepository.save(comment);
        return new CommentResponseDto(comment.getId(), comment.getPostId(), comment.getContent());
    }

    public List<CommentResponseDto> getAllCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId).stream()
            .map(comment -> new CommentResponseDto(comment.getId(), comment.getPostId(), comment.getContent()))
            .collect(Collectors.toList());
    }

    public CommentResponseDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        return new CommentResponseDto(comment.getId(), comment.getPostId(), comment.getContent());
    }

    public CommentResponseDto updateComment(Long id, UpdateCommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        comment = Comment.builder()
            .id(comment.getId())
            .postId(comment.getPostId())
            .content(requestDto.content())
            .build();
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getId(), comment.getPostId(), comment.getContent());
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
