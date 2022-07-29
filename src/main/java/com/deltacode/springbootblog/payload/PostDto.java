package com.deltacode.springbootblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    //title should not be null or empty
    //title should have at least 2 characters
    @NotEmpty(message = "Title is required")
    @Size(min = 2, message = "Title must have at least 2 characters")
    private String title;
    //content should not be null or empty
    @NotEmpty(message = "Content is required")
    private String content;
    //post description should not be null or empty
    //post description should have at least 10 characters
    @NotEmpty(message = "Description is required")
    @Size(min = 10, message = "Description must have at least 10 characters")
    private String description;
    private Set<CommentDto> comment;
}
