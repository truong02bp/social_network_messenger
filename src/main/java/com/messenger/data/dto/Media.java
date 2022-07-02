package com.messenger.data.dto;

import lombok.Data;

@Data
public class Media {
    private Long id;
    private String name;
    private String contentType;
    private String url;
}
