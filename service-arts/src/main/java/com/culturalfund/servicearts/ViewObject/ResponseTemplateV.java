package com.culturalfund.servicearts.ViewObject;

import com.culturalfund.servicearts.repo.model.Art;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateV {

    private Art art;
    private User user;
}
