package com.deltacode.springbootblog.service.impl;

import com.deltacode.springbootblog.entity.Post;
import com.deltacode.springbootblog.exception.ResourceNotFoundException;
import com.deltacode.springbootblog.payload.PostDto;
import com.deltacode.springbootblog.payload.PostResponse;
import com.deltacode.springbootblog.repository.PostRepository;
import com.deltacode.springbootblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        //convert Dto to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        //convert entity to Dto
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        org.springframework.data.domain.Pageable pageable=PageRequest.of(pageNo,pageSize, sort);
        //create a pageable instance
//        Pageable pageable =PageRequest.of(pageNo,pageSize);

        Page<Post> posts = postRepository.findAll(pageable);

        //get content for page object
        List<Post> listOfPost = posts.getContent();

        List<PostDto> content = listOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse =new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getNumberOfElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;


    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        //get post by id from database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post updatedPost = postRepository.save(post);
        mapToDto(updatedPost);


        return null;
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.deleteById(id);

    }


    //convert entity to Dto
    private PostDto mapToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);


//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setContent(post.getContent());
//        postDto.setDescription(post.getDescription());
        return postDto;

    }

    //convert Dto to Entity
    private Post mapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());
        return post;

    }


}
