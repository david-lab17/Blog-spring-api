package com.deltacode.springbootblog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
@ApiModel(description = "Post object")
@Data
public class PostDto {
    @ApiModelProperty(value = "Post id")
    private Long id;
    //title should not be null or empty
    //title should have at least 2 characters
    @NotEmpty(message = "Title is required")
    @Size(min = 2, message = "Title must have at least 2 characters")
    @ApiModelProperty(value = "Post title")
    private String title;
    //content should not be null or empty
    @NotEmpty(message = "Content is required")
    @ApiModelProperty(value = "Post content")
    private String content;
    //post description should not be null or empty
    //post description should have at least 10 characters
    @NotEmpty(message = "Description is required")
    @Size(min = 10, message = "Description must have at least 10 characters")
    @ApiModelProperty(value = "Post description")
    private String description;
    private Set<CommentDto> comment;
}
