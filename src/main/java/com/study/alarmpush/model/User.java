package com.study.alarmpush.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@Entity(name = "userInfo")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Embedded
    @Column(nullable = false)
    private Login login;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Alarm> alarms;

    public User(Long id, String nickName, Login login, List<Alarm> alarms) {
        this.id = id;
        this.nickName = nickName;
        this.login = login;
        this.alarms = alarms;
    }

    public User() {

    }
}
