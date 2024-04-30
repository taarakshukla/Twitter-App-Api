package com.oop.twitter_app.Controller;

import com.oop.twitter_app.Entities.Comment;
import com.oop.twitter_app.Entities.Post;
import com.oop.twitter_app.Outputs.CommentsOutput;
import com.oop.twitter_app.Outputs.ErrorMessage;
import com.oop.twitter_app.Outputs.PostsOutput;
import com.oop.twitter_app.Repositories.CommentRepository;
import com.oop.twitter_app.Repositories.PostRepository;
import com.oop.twitter_app.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        Long userId = post.getUserID(); // Extract userID from JSON
        post.setUserID(userId); // Set userID in the Post object
        return postService.createPost(post);
    }

    @GetMapping
    public ResponseEntity<?> getPostById(@RequestParam Long postID) {
        Post post = postService.getPostById(postID);
        try {
            if (post != null) {
                PostsOutput postOutput = new PostsOutput();
                postOutput.setPostID(post.getPostID());
                postOutput.setPostBody(post.getPostBody());
                postOutput.setDate(post.getDate());

                List<Comment> comment = post.getComments();
                List<CommentsOutput> commentsOutput = new ArrayList<CommentsOutput>();
                for (Comment c : comment) {
                    CommentsOutput commentOutput = new CommentsOutput();
                    commentOutput.setCommentID(c.getCommentId());
                    commentOutput.setCommentBody(c.getCommentBody());
                    commentOutput.setCommentCreator(c.getCommentCreator());
                    commentsOutput.add(commentOutput);
                }
                postOutput.setComments(commentsOutput);
                return ResponseEntity.ok(postOutput);

            } else {
                ErrorMessage errorMessage = new ErrorMessage("Post does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
        }
        catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


    @PatchMapping
    public ResponseEntity<?> editPost(@RequestBody Post post) {
        return postService.editPost(post);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam Long postID) {
        return postService.deletePost(postID);
    }
}
