package com.culturalfund.servicestock.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockDto {
    private String name;
    private Boolean availability;
    private Long quantity;
}
