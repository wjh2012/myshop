package com.ggomg.myshop.post.repository;


import com.ggomg.myshop.post.entity.Post;

import java.util.List;

public interface PostRepository {


    void create(Post post);

    Post read(Long id);

    List<Post> findByTitle(String title);

    List<Post> findAll();

    List<Post> findByMember();
}
