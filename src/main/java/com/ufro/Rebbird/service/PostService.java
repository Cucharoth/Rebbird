package com.ufro.Rebbird.service;

import com.ufro.Rebbird.repository.CommentRepository;
import com.ufro.Rebbird.repository.PostRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufro.Rebbird.commons.GenericService;
import com.ufro.Rebbird.model.Post;

@Service
public class PostService extends GenericService<Post, Long> {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public PostService(PostRepository repository) {
        super(repository);
    }

    public List<Post> getPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public Iterable<Post> findAllByOrderByDateDesc() {
        return postRepository.findAllByOrderByDateDesc();
    }

    public Iterable<Post> findAllByCategoryIdOrderByDateDesc(int categoryId) {
        return postRepository.findAllByCategoryIdOrderByDateDesc(categoryId);
    }

    public int countAllByPostId(Long postId){
        return commentRepository.countAllByPostId(postId);
    }
}
