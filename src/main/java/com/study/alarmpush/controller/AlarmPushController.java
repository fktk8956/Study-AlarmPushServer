package com.study.alarmpush.controller;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.model.*;
import com.study.alarmpush.service.UserManagerService;
import com.study.alarmpush.service.impl.SlackPushServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alarmpush/v1")
public class AlarmPushController {
    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SlackPushServiceImpl slackPushService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AddUserRequestVO reqUser) {
        return userManagerService.registerUser(reqUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUserRequestVO reqUser) throws ResourceNotFoundException {
        return userManagerService.loginUser(reqUser);
    }

    @PostMapping("/pms/add")
    public ResponseEntity<?> createPushMessage(@Valid @RequestBody CreateAlarmRequestVO createAlarmRequestVO) throws ResourceNotFoundException {
        return slackPushService.createPushMessage(createAlarmRequestVO);
    }

    @GetMapping("/pms")
    public ResponseEntity<?> retrieveAllPushMessageByEmail(@RequestParam String email) throws ResourceNotFoundException {
        return userManagerService.retrieveAllPushMessageByEmail(email);
    }

    @PostMapping("/pms/update/{id}")
    public ResponseEntity<?> updatePushMessageById(@PathVariable Long id, @Valid @RequestBody UpdateAlarmRequestVO updateAlarmRequestVO) throws ResourceNotFoundException {
        return slackPushService.updatePushMessage(id, updateAlarmRequestVO);
    }

    @DeleteMapping("/pms/delete/{id}")
    public ResponseEntity<?> deletePushMessageById(@PathVariable Long id) {
        return slackPushService.deletePushMessage(id);
    }

    @DeleteMapping("/pms/delete")
    public ResponseEntity<?> deleteAllPushMessages(@Valid @RequestBody DeleteAllAlarmsRequestVO requestVO) throws ResourceNotFoundException {
        return slackPushService.deleteAllPushMessages(requestVO);
    }
}
