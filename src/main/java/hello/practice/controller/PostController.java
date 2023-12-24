package hello.practice.controller;

import hello.practice.dto.CreatePostRequestDto;
import hello.practice.dto.PostResponseDto;
import hello.practice.dto.UpdatePostRequestDto;
import hello.practice.service.JwtService;
import hello.practice.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostRequestDto createPostRequestDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(403).body("Access Denied: No Token Provided");
        }

        try {
            String username = jwtService.validateTokenAndGetUsername(token);
            // 토큰 유효성 검증 후 게시글 작성 로직
            postService.createPost(createPostRequestDto, username);
            return ResponseEntity.ok().body("게시물이 성공적으로 생성되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body("Invalid Token");
        }
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPostsSortedByCreatedAtDesc(); // 정렬된 게시글 목록 반환
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        return postService.getPostById(id)
            .map(postResponseDto -> ResponseEntity.ok(postResponseDto))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody UpdatePostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
