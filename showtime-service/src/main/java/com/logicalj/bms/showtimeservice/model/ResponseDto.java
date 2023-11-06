package com.logicalj.bms.showtimeservice.model;

import lombok.Data;

@Data
public class ResponseDto {
    Boolean success;
    String message;

    public ResponseDto(Boolean success, String message) {
        this.message = message;
        this.success = success;
    }
}
