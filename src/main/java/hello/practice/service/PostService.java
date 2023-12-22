package hello.practice.service;

import hello.practice.dto.CreatePostRequestDto;
import hello.practice.dto.PostResponse;
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

    public PostResponse createPost(CreatePostRequestDto requestDto) {
        Post post = postRepository.save(new Post(requestDto.title(), requestDto.content()));
        return new PostResponse(post.getId(), post.getTitle(), post.getContent());
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
            .map(post -> new PostResponse(post.getId(), post.getTitle(), post.getContent()))
            .collect(Collectors.toList());
    }

}
