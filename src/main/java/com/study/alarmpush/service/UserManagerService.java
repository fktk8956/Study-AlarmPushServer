package com.study.alarmpush.service;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.model.*;
import com.study.alarmpush.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class UserManagerService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> registerUser(AddUserRequestVO reqUser) {
        try {
            if (userRepository.findByLoginEmail(reqUser.getEmail()).isPresent()) {
                return new ResponseEntity("email is already registered", HttpStatus.CONFLICT);
            }

            User addUser = User.builder()
                    .nickName(reqUser.getNickName())
                    .login(Login.builder()
                            .email(reqUser.getEmail())
                            .password(reqUser.getPassword())
                            .createDate(LocalDateTime.now())
                            .build())
                    .build();

            userRepository.save(addUser);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ToDo. JWT 토큰 로직 반영해야해
    public ResponseEntity<?> loginUser(LoginUserRequestVO reqUser) throws ResourceNotFoundException {
        Map<String, String> ret = new HashMap<>();
        try {
            Optional<User> userOptional = Optional.ofNullable(userRepository.findByLoginEmailAndLoginPassword(reqUser.getEmail(), reqUser.getPassword())
                    .orElseThrow(() -> new ResourceNotFoundException(reqUser.getEmail() + " is not founded")));

            ret.put("result", "SUCCESS");
            ret.put("nickName", userOptional.get().getNickName());
            ret.put("token", "test");
            return new ResponseEntity(ret, HttpStatus.OK);
        } catch (ResourceNotFoundException re) {
            log.error(re.getMessage());
            throw re;
        }
    }

    public ResponseEntity<?> retrieveAllPushMessageByEmail(final String email) throws ResourceNotFoundException {
        Map<String, String> ret = new HashMap<>();
        try {
            Optional<User> userOptional = Optional.ofNullable(userRepository.findByLoginEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException(email + " is not founded")));
            if(userOptional.isPresent()) {
                List<Alarm> alarmList = userOptional.get().getAlarms();
                List<FindAlarmDTO> findAlarmDTOList = new ArrayList<>();
                alarmList.forEach(alarm -> {
                    findAlarmDTOList.add(FindAlarmDTO.builder()
                            .id(alarm.getId())
                            .message(alarm.getMessage())
                            .createDate(alarm.getCreateDate().toString())
                            .build());
                });
                ret.put("result", "SUCCESS");
                ret.put("data", new Gson().toJson(findAlarmDTOList));
            } else {
                ret.put("result", "FAIL");
                ret.put("data", "");
            }
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (ResourceNotFoundException re) {
            log.error(re.getMessage());
            throw re;
        }
    }

    public Optional<User> findUserByEmail(final String email) throws ResourceNotFoundException {
        try {
            Optional<User> userOptional = Optional.ofNullable(userRepository.findByLoginEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException(email + " is not founded")));
            return userOptional;
        } catch (ResourceNotFoundException re) {
            log.error(re.getMessage());
            throw re;
        }
    }

    public void updateUser(User updatedUserInfo) {
        try {
            userRepository.save(updatedUserInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("update User is failed.");
        }
    }
}
