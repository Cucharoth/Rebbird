package com.ufro.Rebbird.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "publicacion")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publicacion_id", nullable = false)
    private Long id;

    @Column(name = "titulo_publicacion", nullable = false, length = 70)
    private String title;

    @Column(name = "fecha_publicacion", nullable = false, columnDefinition = "DATE")
    private Date date;

    @Column(name = "content_publicacion", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    public Post(Long id, String title, Date date, String content, User user) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.user = user;
    }

    public Post() {
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
