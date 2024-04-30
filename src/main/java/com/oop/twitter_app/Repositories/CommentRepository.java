package com.oop.twitter_app.Repositories;

import com.oop.twitter_app.Entities.Comment;
import com.oop.twitter_app.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
