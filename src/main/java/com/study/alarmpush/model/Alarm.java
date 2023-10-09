package com.study.alarmpush.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Alarm {
    @Id
    @GeneratedValue
    private Long id;

    private String message;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
