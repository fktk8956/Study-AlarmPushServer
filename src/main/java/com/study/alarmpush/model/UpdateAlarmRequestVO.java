package com.study.alarmpush.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAlarmRequestVO {
    @NotBlank
    @NotNull
    private String message;
}
