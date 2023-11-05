package com.study.alarmpush.controller;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.model.*;
import com.study.alarmpush.service.UserManagerService;
import com.study.alarmpush.service.impl.SlackPushServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "알람 푸쉬 메시지 서버", description = "Slack에 text를 알림할 수 있는 서버")
@RestController
@RequestMapping("/alarmpush/v1")
public class AlarmPushController {
    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SlackPushServiceImpl slackPushService;

    @Operation(summary = "회원가입", description = "유저 정보를 통해 회원가입한다.\n email 중복방지!")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AddUserRequestVO reqUser) {
        return userManagerService.registerUser(reqUser);
    }

    @Operation(summary = "로그인", description = "")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "로그인 성공, nickName 반환, 토큰 반환(개발 중)")})
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
