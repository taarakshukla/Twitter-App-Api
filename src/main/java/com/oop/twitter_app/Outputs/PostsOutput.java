package com.oop.twitter_app.Outputs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostsOutput {
    private Long postID;
    private String postBody;
//    private LocalDateTime date;
    private String date;
    private List<CommentsOutput> comments;

    public PostsOutput() {
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date.toLocalDate().format(DateTimeFormatter.ISO_DATE);
    }
    public List<CommentsOutput> getComments() {
        return comments;
    }

    public void setComments(List<CommentsOutput> comments) {
        this.comments = comments;
    }
}

