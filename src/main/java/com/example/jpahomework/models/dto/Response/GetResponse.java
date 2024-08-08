package com.example.jpahomework.models.dto.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class GetResponse {
    public static ResponseEntity<?> responseAll(String message, Object data, Integer total) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponseALL.builder()
                .message(message)
                .totalRecords(total)
                .payload(data)
                .time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .build());
    }

    public static ResponseEntity<?> responseOnce(String message, Object data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponseOnce.builder()
                .message(message)
                .payload(data)
                .time(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .build());
    }

    public static ResponseEntity<?> responseModify(String message, Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponseOnce.builder()
                        .message(message)
                        .payload(data)
                        .time(LocalDateTime.now())
                        .status(HttpStatus.OK)
                .build());
    }
}
