package com.deltacode.springbootblog.service.impl;

import com.deltacode.springbootblog.entity.Comment;
import com.deltacode.springbootblog.entity.Post;
import com.deltacode.springbootblog.exception.BlogApiException;
import com.deltacode.springbootblog.exception.ResourceNotFoundException;
import com.deltacode.springbootblog.payload.CommentDto;
import com.deltacode.springbootblog.repository.CommentRepository;
import com.deltacode.springbootblog.repository.PostRepository;
import com.deltacode.springbootblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImp(CommentRepository commentRepository,
                             PostRepository postRepository,
                             ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment= mapToEntity(commentDto);
        //retrieve post from postId
        Post post =postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
        //set post to comment
        comment.setPost(post);
        //save comment to database
        Comment newComment=commentRepository.save(comment);


        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getAllComments(Long postId) {
        //retrieve comments from postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        //map to dto
        return  comments.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //retrieve post from postId
        Post post =postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
        //retrieve comment by commentId
Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        //check if comment belongs to post
if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest ) {
        //retrieve post from postId
        Post post =postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
        //retrieve comment by commentId
Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        //check if comment belongs to post
if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        //update comment
   comment.setName(commentRequest.getName());
    comment.setEmail(commentRequest.getEmail());
    comment.setBody(commentRequest.getBody());
    Comment updatedComment=commentRepository.save(comment);


        return mapToDto(updatedComment);
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        //retrieve post from postId
        Post post =postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
        //retrieve comment by commentId
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        //check if comment belongs to post
if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        //delete comment
        commentRepository.delete(comment);
    }


    //map entity to dto
    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }
    //map dto to entity
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
