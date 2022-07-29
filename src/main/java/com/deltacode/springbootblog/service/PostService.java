package com.deltacode.springbootblog.service;

import com.deltacode.springbootblog.entity.Post;
import com.deltacode.springbootblog.payload.PostDto;
import com.deltacode.springbootblog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir );
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto,Long id);
    void deletePostById(Long id);


}
