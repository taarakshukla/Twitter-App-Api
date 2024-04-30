package com.oop.twitter_app.Controller;

import java.io.IOException;
import com.oop.twitter_app.Entities.Comment;
import com.oop.twitter_app.Entities.Post;
import com.oop.twitter_app.Entities.User;
import com.oop.twitter_app.Outputs.CommentsOutput;
import com.oop.twitter_app.Outputs.ErrorMessage;
import com.oop.twitter_app.Outputs.PostsOutput;
import com.oop.twitter_app.Outputs.UsersOutput;
import com.oop.twitter_app.Repositories.CommentRepository;
import com.oop.twitter_app.Repositories.PostRepository;
import com.oop.twitter_app.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        return userService.loginUser(email, password);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetail(@RequestParam Long userID) {
        try{
            User user = userService.getUserDetail(userID);
            if (user != null) {

                UsersOutput userOutput = new UsersOutput();
                String email = user.getEmail();
                userOutput.setEmail(email);
                userOutput.setUserID(user.getId());
                userOutput.setName(user.getName());

                return ResponseEntity.ok(userOutput);
            } else {

                ErrorMessage errorMessage = new ErrorMessage("User does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
        }
        catch(Exception e){
            ErrorMessage errorMessage = new ErrorMessage("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/users")
    public List<UsersOutput> getAllUsers() {

        List<User> users = userService.getAllUsers();
        List<UsersOutput> outputs = new ArrayList<>();
        try {
            for (User user : users) {
                UsersOutput userOutput = new UsersOutput();
                userOutput.setEmail(user.getEmail());
                userOutput.setUserID(user.getId());
                userOutput.setName(user.getName());
                outputs.add(userOutput);
            }
            return outputs;
        }
        catch(Exception e){
            return outputs;
        }
    }

    @GetMapping("/")
    public List<PostsOutput> getAllPostsSortedByDate() {
        List<Post> posts = postRepository.findAllByOrderByDateDesc();
        List<PostsOutput> outputs = new ArrayList<>();

        try {
            for (Post post : posts) {
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
                outputs.add(postOutput);
            }
            return outputs;
        }
        catch(Exception e){
            return outputs;
        }
    }
}
