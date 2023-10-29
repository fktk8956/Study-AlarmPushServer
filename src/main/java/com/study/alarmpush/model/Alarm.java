package com.study.alarmpush.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
