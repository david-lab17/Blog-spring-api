package com.deltacode.springbootblog.controller;

import com.deltacode.springbootblog.payload.CommentDto;
import com.deltacode.springbootblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("api/{postId}/comment")
    public ResponseEntity<CommentDto>createComment(@PathVariable(name = "postId") Long postId,
                                                  @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    @GetMapping("api/{postId}/comments")

    public ResponseEntity<List<CommentDto>>getCommentsByPostId(@PathVariable(name = "postId") Long postId){
        return new ResponseEntity<>(commentService.getAllComments(postId), HttpStatus.OK);
    }
    @GetMapping("api/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto>getCommentById(@PathVariable(name = "postId") Long postId,
                                                     @PathVariable(name = "commentId") Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }
    @PutMapping("api/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") Long postId,
                                                     @PathVariable(name = "commentId") Long commentId,
                                                     @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }
    @DeleteMapping("api/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable(name = "postId") Long postId,
                                                   @PathVariable(name = "commentId") Long commentId){
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
