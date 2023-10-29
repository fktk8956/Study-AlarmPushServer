package com.study.alarmpush.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAlarmRequestVO {
    @NotNull
    @NotBlank
    private String email;

    @Size(min = 1)
    private String message;
}
