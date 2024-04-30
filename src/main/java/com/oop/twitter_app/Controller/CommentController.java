package com.oop.twitter_app.Controller;

import com.oop.twitter_app.Entities.Comment;
import com.oop.twitter_app.Outputs.CommentsOutput;
import com.oop.twitter_app.Outputs.ErrorMessage;
import com.oop.twitter_app.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @GetMapping
    public ResponseEntity<?> getCommentById(@RequestParam Long commentID) {
        Comment comment = commentService.getCommentById(commentID);
        try {
            if (comment != null) {

                CommentsOutput commentOutput = new CommentsOutput();
                commentOutput.setCommentID(comment.getCommentId());
                commentOutput.setCommentBody(comment.getCommentBody());
                commentOutput.setCommentCreator(comment.getCommentCreator());

                return ResponseEntity.ok(commentOutput);
            } else {
                ErrorMessage errorMessage = new ErrorMessage("Comment does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
        }
        catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PatchMapping
    public ResponseEntity<?> editComment(@RequestBody Comment comment) {
        return commentService.editComment(comment);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestParam Long commentID) {
        return commentService.deleteComment(commentID);
    }
}
