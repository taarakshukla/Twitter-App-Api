package com.oop.twitter_app.Outputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.oop.twitter_app.Entities.CommentCreator;


public class CommentsOutput {
    public Long commentID;
    private String commentBody;
    private CommentCreator commentCreator;

    public CommentsOutput() {
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public CommentCreator getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(CommentCreator commentCreator) {
        this.commentCreator = commentCreator;
    }
}
