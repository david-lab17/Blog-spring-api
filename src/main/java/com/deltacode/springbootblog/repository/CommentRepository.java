package com.deltacode.springbootblog.repository;

import com.deltacode.springbootblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {
    //list of comments by post id
    List<Comment> findByPostId(long postId);


}

