package hello.practice.service;

import hello.practice.dto.CreatePostRequestDto;
import hello.practice.dto.PostResponseDto;
import hello.practice.dto.UpdatePostRequestDto;
import hello.practice.entity.Post;
import hello.practice.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(CreatePostRequestDto requestDto, String username) {
        Post post = postRepository.save(Post.builder()
            .title(requestDto.title())
            .nickname(username)
            .content(requestDto.content())
            .build());
        return new PostResponseDto(post.getId(),post.getTitle(),post.getNickname(), post.getContent(), post.getCreatedAt());
    }

    public List<PostResponseDto> getAllPostsSortedByCreatedAtDesc() {
        // createdAt 기준 내림차순 정렬 적용
        List<Post> posts = postRepository.findAll(Sort.by(Direction.DESC, "createdAt"));
        return posts.stream()
            .map(post -> new PostResponseDto(post.getId(), post.getTitle(), post.getNickname(), post.getContent(), post.getCreatedAt()))
            .collect(Collectors.toList());
    }

    public Optional<PostResponseDto> getPostById(Long id) {
        return postRepository.findById(id)
            .map(post -> new PostResponseDto(post.getId(), post.getTitle(), post.getNickname(), post.getContent(), post.getCreatedAt()));
    }

    public void updatePost(Long id, UpdatePostRequestDto requestDto, String username) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        if (!post.getNickname().equals(username)) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
