package com.deltacode.springbootblog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(description = "Sign up requirements")
public class SignUpDto {
    @ApiModelProperty(value = "User name")
    private String name;
    @ApiModelProperty(value = "Username")
    private String username;
    @ApiModelProperty(value = "Email")
    private String email;
    @ApiModelProperty(value = "Password")
    private String password;
}
