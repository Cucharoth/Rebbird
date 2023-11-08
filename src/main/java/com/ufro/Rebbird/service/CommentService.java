package com.ufro.Rebbird.service;

import org.springframework.stereotype.Service;

import com.ufro.Rebbird.model.Comment;
import com.ufro.Rebbird.repository.CommentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Iterable<Comment> findAllByPostIdOrderByDate(Long id) {
        return commentRepository.findAllByPostIdOrderByDate(id);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
    
    public int countAllByPostId(Long postId) {
        return commentRepository.countAllByPostId(postId);
    }
}
