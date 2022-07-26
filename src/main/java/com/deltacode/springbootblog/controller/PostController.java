package com.deltacode.springbootblog.controller;

import com.deltacode.springbootblog.entity.Post;
import com.deltacode.springbootblog.payload.PostDto;
import com.deltacode.springbootblog.payload.PostResponse;
import com.deltacode.springbootblog.service.PostService;
import com.deltacode.springbootblog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Api(value = "Post Controller Rest Api")
@RestController()
@RequestMapping(path = "/api/posts")
public class PostController {

    @Autowired
    private PostService postService;



    //create blog post
    @ApiOperation(value = "Create Blog Post Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return  new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    //Get all post
    @ApiOperation(value = "Fetching all Blog Post Api")
@GetMapping
    public PostResponse getAllPosts(
        @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
        @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
        @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
        @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
){
       return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
}
    @ApiOperation(value = "Fetching all Blog Post Api by Id")

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getTransactionByAccountNo(@PathVariable("id") Long id) {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
    @ApiOperation(value = "Updating Blog Post Api by Id")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name ="id")long id){
        PostDto postResponse=postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    //delete post api
    @ApiOperation(value = "Delete Blog Post by Id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePost(@PathVariable(name = "id")long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successful.",HttpStatus.OK);
    }


     
}
