package com.ufro.Rebbird.service;

import org.springframework.stereotype.Service;

import com.ufro.Rebbird.model.UserPostReaction;
import com.ufro.Rebbird.repository.UserPostReactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserPostReactionService {

    private final UserPostReactionRepository userPostReactionRepository;

    public void saveOrUpdate(UserPostReaction userPostReaction) {
        userPostReactionRepository.save(userPostReaction);
    }

    public void delete(UserPostReaction userPostReaction) {
        userPostReactionRepository.delete(userPostReaction);
    }

    public UserPostReaction findByPostAndUser(Long postId, int userId) {
        return userPostReactionRepository.findByPostIdAndUserId(postId, userId);
    }
}
