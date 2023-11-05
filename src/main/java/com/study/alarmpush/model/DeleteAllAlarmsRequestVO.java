package com.study.alarmpush.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteAllAlarmsRequestVO {
    @NotBlank
    @NotNull
    private String email;
}
