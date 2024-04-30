package com.oop.twitter_app.Services;

import com.oop.twitter_app.Entities.Post;
import com.oop.twitter_app.Entities.User;
import com.oop.twitter_app.Outputs.ErrorMessage;
import com.oop.twitter_app.Repositories.PostRepository;
import com.oop.twitter_app.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createPost(Post post) {
        Long userId = post.getUserID(); // Get userID from the Post object
        Long postId=post.getPostID();

        Optional<User> userOptional = userRepository.findById(userId);
        try {
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                post.setUser(user);
                post.setDate(LocalDateTime.now());
                post.setUserID(userId);
                user.getPosts().add(post);
                postRepository.save(post);
                String success = "Post created successfully";
                return ResponseEntity.ok(success);
            } else {
                ErrorMessage errorMessage = new ErrorMessage("User does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
        }
        catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public Post getPostById(Long postID) {
        try {
            Optional<Post> postOptional = postRepository.findById(postID);
            return postOptional.orElse(null);
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Post> getAllPostsSortedByDate() {
        return postRepository.findAllByOrderByDateDesc();
    }

    public ResponseEntity<?> editPost(Post post) {
        Long postId = post.getPostID();
        if (!postRepository.existsById(post.getPostID())) {
            ErrorMessage errorMessage = new ErrorMessage("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

        }
        Post existingPost = postRepository.findById(postId).orElse(null);
        if (existingPost == null) {
            ErrorMessage errorMessage = new ErrorMessage("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

        }
        existingPost.setPostBody(post.getPostBody());
        postRepository.save(existingPost);
        String success = "Post edited successfully";
        return ResponseEntity.ok(success);

    }

    public ResponseEntity<?> deletePost(Long postID) {
        if (!postRepository.existsById(postID)) {
            ErrorMessage errorMessage = new ErrorMessage("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

        }
        postRepository.deleteById(postID);
        String success = "Post deleted";
        return ResponseEntity.ok(success);
    }
}