package com.ufro.Rebbird.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(name = "fecha_publicacion")
    private Date date;

    @Column(name = "content_publicacion", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "cant_comentarios", columnDefinition = "INT default 0")
    private int commentsAmount;

    @Column(name = "cant_reaccion", columnDefinition = "INT default 0")
    private int reactionAmount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @ManyToOne
    @JoinColumn(name = "categoria_id", columnDefinition = "INT default 1")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comment = new HashSet<>();

    public Post(Long id, String title, Date date, String content, int commentsAmount, int reactionAmount, User author,
            Category category, Set<Comment> comment) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.commentsAmount = commentsAmount;
        this.reactionAmount = reactionAmount;
        this.author = author;
        this.category = category;
        this.comment = comment;
    }

    public int getCommentsAmount() {
        return commentsAmount;
    }

    public void setCommentsAmount(int commentsAmount) {
        this.commentsAmount = commentsAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getReactionAmount() {
        return reactionAmount;
    }

    public void setReactionAmount(int reactionAmount) {
        this.reactionAmount = reactionAmount;
    }

    

}
