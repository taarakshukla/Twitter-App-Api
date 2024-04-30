package com.oop.twitter_app.Services;

import com.oop.twitter_app.Entities.Comment;
import com.oop.twitter_app.Entities.Post;
import com.oop.twitter_app.Entities.User;
import com.oop.twitter_app.Outputs.ErrorMessage;
import com.oop.twitter_app.Repositories.CommentRepository;
import com.oop.twitter_app.Repositories.PostRepository;
import com.oop.twitter_app.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createComment(Comment comment) {
        // Retrieve post and user entities based on postID and userID
        Post post = postRepository.findById(comment.getPostID()).orElse(null);
        User user = userRepository.findById(comment.getUserID()).orElse(null);

        // Check if post and user exist
        if (post != null && user != null) {
            // Set post and user for the comment
            comment.setPost(post);
            comment.setUser(user);

            commentRepository.save(comment);
            String success = "Comment created successfully";
            return ResponseEntity.ok(success);

        } else if(user==null){
            ErrorMessage errorMessage = new ErrorMessage("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        else{
            ErrorMessage errorMessage = new ErrorMessage("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public Comment getCommentById(Long commentId) {
        try {
            Optional<Comment> commentOptional = commentRepository.findById(commentId);
            return commentOptional.orElse(null);
        }
        catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<?> editComment(Comment comment) {
        if (!commentRepository.existsById(comment.getCommentId())) {
            ErrorMessage errorMessage = new ErrorMessage("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        Comment existingComment = commentRepository.findById(comment.getCommentId()).orElse(null);
        if (existingComment == null) {
            ErrorMessage errorMessage = new ErrorMessage("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        existingComment.setCommentBody(comment.getCommentBody());
        commentRepository.save(existingComment);
        String success = "Comment edited successfully";
        return ResponseEntity.ok(success);
    }

    public ResponseEntity<?> deleteComment(Long comment) {
        if (!commentRepository.existsById(comment)) {
            ErrorMessage errorMessage = new ErrorMessage("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        commentRepository.deleteById(comment);
        String success = "Comment deleted";
        return ResponseEntity.ok(success);
    }
}
