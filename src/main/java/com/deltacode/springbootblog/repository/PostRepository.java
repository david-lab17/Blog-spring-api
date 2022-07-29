package com.deltacode.springbootblog.repository;

import com.deltacode.springbootblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {


}
