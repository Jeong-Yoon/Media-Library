package com.inzent.medialibrary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageDTO {
    private Long content_id;
    private String content_origin_name;
    private String content_reg_user;
    private String user_name;
    private String content_reg_date;
    private String content_storage;
    private byte[] content;
    private String content_type;
    private String content_save_name;
    private String content_share;
    private long content_size;
    private long folder_id;
    private String path;
    private String content_attribute;
}
