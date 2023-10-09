package com.study.alarmpush.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequestVO {
    @NotBlank
    @NotNull
    private String nickName;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;
}
