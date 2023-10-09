package com.study.alarmpush.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserRequestVO {
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;
}
