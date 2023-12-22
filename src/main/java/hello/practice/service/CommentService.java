package hello.practice.service;

import hello.practice.dto.CommentResponseDto;
import hello.practice.dto.CreateCommentRequestDto;
import hello.practice.entity.Comment;
import hello.practice.repository.CommentRepository;
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

}