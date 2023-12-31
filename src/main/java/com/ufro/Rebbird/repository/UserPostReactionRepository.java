package com.ufro.Rebbird.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufro.Rebbird.model.Post;
import com.ufro.Rebbird.model.UserPostReaction;

@Repository
public interface UserPostReactionRepository extends CrudRepository<UserPostReaction, Post> {

    UserPostReaction findByPostIdAndUserId(Long postId, int userId);
}
