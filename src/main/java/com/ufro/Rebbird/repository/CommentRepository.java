package com.ufro.Rebbird.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufro.Rebbird.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    
    Iterable<Comment> findAllByPostIdOrderByDate(Long postId);

    public int countAllByPostId(Long postId);
}
