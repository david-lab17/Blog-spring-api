package com.deltacode.springbootblog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp,
                        String message,
                        String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
