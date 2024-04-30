package com.oop.twitter_app.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("commentID")
    private Long commentId;

    private String commentBody;
    private Long postID;
    private Long userID;

    @JsonIgnore
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    // Constructor
    public Comment() {
    }

    // Getters and setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long id) {
        this.commentId = id;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
    @JsonProperty("commentCreator")
    public CommentCreator getCommentCreator() {
        if (user != null) {
            CommentCreator creator = new CommentCreator();
            creator.setUserID(user.getId());
            creator.setName(user.getName());
            return creator;
        } else {
            return null;
        }
    }
}

