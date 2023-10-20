package com.ufro.Rebbird.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPostReactionId implements Serializable {

    private Post post;
    private User user;
}
