package com.ufro.Rebbird.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufro.Rebbird.model.UserPostReaction;

@Repository
public interface UserPostReactionRepository extends CrudRepository<UserPostReaction, Long> {

    UserPostReaction findByPostIdAndUserId(Long postId, int userId);
}
