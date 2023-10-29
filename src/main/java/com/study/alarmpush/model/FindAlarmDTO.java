package com.study.alarmpush.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindAlarmDTO {
    private Long id;
    private String message;
    private String createDate;
}
