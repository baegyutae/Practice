package hello.practice.service;

import hello.practice.dto.CreatePostRequestDto;
import hello.practice.dto.PostResponse;
import hello.practice.entity.Post;
import hello.practice.repository.PostRepository;
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

}
