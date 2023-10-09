package com.study.alarmpush.repository;

import com.study.alarmpush.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginEmail(String email);

    Optional<User> findByLoginEmailAndLoginPassword(String email, String password);
}
