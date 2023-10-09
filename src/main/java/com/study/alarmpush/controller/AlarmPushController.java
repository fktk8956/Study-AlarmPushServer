package com.study.alarmpush.controller;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.model.AddUserRequestVO;
import com.study.alarmpush.model.LoginUserRequestVO;
import com.study.alarmpush.service.UserManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarmpush/v1")
public class AlarmPushController {
    @Autowired
    private UserManagerService userManagerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AddUserRequestVO reqUser) {
        return userManagerService.registerUser(reqUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUserRequestVO reqUser) throws ResourceNotFoundException {
        return userManagerService.loginUser(reqUser);
    }
}
