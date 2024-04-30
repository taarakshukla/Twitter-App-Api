package com.oop.twitter_app.Outputs;

import com.oop.twitter_app.Entities.Post;

import java.util.List;

public class UsersOutput {
    private String email;
    private Long userID;
    private String name;
//    private List<Post> posts;

//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

    public UsersOutput() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

