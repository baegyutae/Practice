package hello.practice.service;

import hello.practice.dto.CreatePostRequestDto;
import hello.practice.dto.PostResponseDto;
import hello.practice.dto.UpdatePostRequestDto;
import hello.practice.entity.Post;
import hello.practice.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(CreatePostRequestDto requestDto) {
        Post post = postRepository.save(new Post(requestDto.title(), requestDto.content()));
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent());
    }

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
            .map(post -> new PostResponseDto(post.getId(), post.getTitle(), post.getContent()))
            .collect(Collectors.toList());
    }

    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent());
    }

    public PostResponseDto updatePost(Long id, UpdatePostRequestDto requestDto) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        Post updatedPost = Post.builder()
            .title(requestDto.title())
            .content(requestDto.content())
            .build();

        postRepository.save(updatedPost);

        return new PostResponseDto(updatedPost.getId(), updatedPost.getTitle(),
            updatedPost.getContent());
    }
}
