package com.deltacode.springbootblog.payload;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean last;



}
