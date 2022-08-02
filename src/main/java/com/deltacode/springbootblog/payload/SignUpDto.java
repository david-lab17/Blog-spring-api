package com.deltacode.springbootblog.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
