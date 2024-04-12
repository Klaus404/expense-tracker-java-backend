package com.example.klaus404.expensemanager.dto.payload.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private Integer errCode;

    private String errMsg;
}
