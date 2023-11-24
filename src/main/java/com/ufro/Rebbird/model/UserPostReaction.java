package com.ufro.Rebbird.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ufro.Rebbird.model.utils.ReactionType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario_reacciona_publicacion")
@IdClass(UserPostReactionId.class)
public class UserPostReaction {

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_reaccion", nullable = false)
    private ReactionType reactionType;

}
