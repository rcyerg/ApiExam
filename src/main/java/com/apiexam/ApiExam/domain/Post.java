package com.apiexam.ApiExam.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name="posts")
public class Post {


    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    @NotEmpty
    @Column(name="post_title")
    private String title;

    @NotEmpty
    @Column(name="post_content")
    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id")
    private List<Comment> comments;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
