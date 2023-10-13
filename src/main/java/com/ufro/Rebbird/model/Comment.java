package com.ufro.Rebbird.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id", nullable = false)
    private Long id;

    @Column(name = "content_comentario", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "fecha_comentario", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

}
