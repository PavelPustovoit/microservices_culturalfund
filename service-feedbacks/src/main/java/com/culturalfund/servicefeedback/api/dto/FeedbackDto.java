package com.culturalfund.servicefeedback.api.dto;

import com.culturalfund.servicefeedback.repo.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedbackDto {

    private String description;
    private long userId;
    private Date dateCreate;
    private Status status;
}
