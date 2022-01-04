package com.culturalfund.servicearts.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Art {
    private String title;
    private String author;
    private Date dateCreate;
    private String style;
    private String technique;
    private String description;
    private long userId;
}
