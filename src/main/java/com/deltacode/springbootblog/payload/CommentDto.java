package com.deltacode.springbootblog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private Long id;
    //name should not be null or empty
    @NotEmpty(message = "Name is required")
    private String name;
    //email should not be null or empty
    //email should have valid format
    @NotEmpty(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;
    //comment should not be null or empty
    //comment should at least 10 characters
    @NotEmpty(message = "Comment is required")
    @Size(min = 10, message = "Comment must have at least 10 characters")
    private String body;
}
