package com.example.project1.controller;


import com.example.project1.domain.dto.Post;
import com.example.project1.domain.dto.Response;
import com.example.project1.domain.dto.post.*;
import com.example.project1.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
@RequiredArgsConstructor // 필요한 생성자

public class PostController {

    private final PostService postService;

    // 포스트 생성
    @PostMapping("")
    public Response<PostCreateResponse> add(@RequestBody PostCreateRequest request, Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName : {}", userName);
        PostCreateResponse post = postService.createPost(request, userName);
        log.info("포스트 생성 성공");

        return ResponseEntity.ok().body(Response.success(new PostCreateResponse(post.getPostId(), post.getMessage()))).getBody();
    }

    // 포스트 전체 리스트
    @GetMapping("")
    public Response<Page<PostSelectResponse>> postList(
            @PageableDefault(size = 20, sort = {"id"}, direction = Sort.Direction.DESC) // 20개 최신순
            Pageable pageable) {

        List<PostSelectResponse> Post = postService.findAllPost(pageable);
        log.info("포스트 리스트 조회 성공");

        return Response.success(allPost);
    }

    // 포스트 리스트
    @GetMapping("/{postsId}")
    public ResponseEntity<PostSelectResponse> postDetail(@PathVariable("postsId") Integer id) {
        PostSelectResponse post = postService.findPost(id);
        log.info("포스트 상세 조회 성공");
        return ResponseEntity.ok().body(post);
    }

    // 포스트 수정
    @PutMapping("/{postsId}")
    public Response<PostUpdateResponse> updatePost(@PathVariable("postsId") Integer id,
                                                   @RequestBody @Valid PostUpdateRequest request,
                                                   Authentication authentication) {
        String userName = authentication.getName();
        Post updatedPost = postService.update(id, userName, request);

        log.info("포스트 수정 성공");
        return Response.success(new PostUpdateResponse(updatedPost.getId(), "포스트 수정 완료"));
    }


    // 포스트 삭제
    @DeleteMapping("/{postsId}")
    public Response<PostDeleteResponse> deletePost(@PathVariable("postsId") Integer id,
                                                   Authentication authentication) {
        String userName = authentication.getName();
        Integer deletedId = postService.delete(id, userName);
        log.info("포스트 삭제 성공");
        return Response.success(new PostDeleteResponse(deletedId, "포스트 삭제 완료"));
    }

}
