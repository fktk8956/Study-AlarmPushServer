package com.study.alarmpush.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@Builder
public class Login {
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createDate;

    public Login(String email, String password, LocalDateTime createDate) {
        this.email = email;
        this.password = password;
        this.createDate = createDate;
    }

    public Login() {

    }
}
