package com.ggomg.myshop.post.service;

import com.ggomg.myshop.member.entity.Member;
import com.ggomg.myshop.member.service.DTO.MemberListResponse;
import com.ggomg.myshop.post.entity.Post;
import com.ggomg.myshop.post.repository.PostRepository;
import com.ggomg.myshop.post.service.DTO.PostCreateRequestToService;
import com.ggomg.myshop.post.service.DTO.PostListResponse;
import com.ggomg.myshop.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = false)
    public Long save(PostCreateRequestToService request) {

        Post post = Post.builder()
                .board(request.getBoard())
                .category(request.getCategory())
                .member(request.getMember())
                .title(request.getTitle())
                .content(request.getContent())
                        .build();

        postRepository.create(post);
        return post.getId();
    }

    @Override
    public List<PostListResponse> findAllPosts() {

        List<Post> findPosts = postRepository.findAll();

        return findPosts.stream()
                .map(PostListResponse::new)
                .collect((Collectors.toList()));
    }

    @Override
    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    @Override
    public List<Post> findByMember(Member member) {
        return postRepository.findByMember(member);
    }

    @Override
    public List<Post> findByContent(String content) {
        return postRepository.findByContent(content);
    }

    @Override
    public List<Post> findByTitleOrContent(String title, String content) {
        return postRepository.findByTitleOrContent(title, content);
    }
}
