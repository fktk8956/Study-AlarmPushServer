package com.study.alarmpush.service.impl;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.model.Alarm;
import com.study.alarmpush.model.CreateAlarmRequestVO;
import com.study.alarmpush.model.UpdateAlarmRequestVO;
import com.study.alarmpush.model.User;
import com.study.alarmpush.repository.AlarmRepository;
import com.study.alarmpush.service.PushService;
import com.study.alarmpush.service.UserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class SlackPushServiceImpl implements PushService {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private AlarmRepository alarmRepository;

    @Override
    public ResponseEntity<?> createPushMessage(CreateAlarmRequestVO requestVO) throws ResourceNotFoundException {
        try {
            User findUser = userManagerService.findUserByEmail(requestVO.getEmail()).get();

            Alarm alarm = Alarm.builder()
                    .message(requestVO.getMessage())
                    .createDate(LocalDateTime.now())
                    .user(findUser)
                    .build();
            findUser.getAlarms().add(alarm);

            userManagerService.updateUser(findUser);
            alarmRepository.save(alarm);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> updatePushMessage(Long id, UpdateAlarmRequestVO requestVO) {
        // 만들기
    }

    @Override
    public ResponseEntity<?> deletePushMessage(User user, Alarm reqAlarm) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAllPushMessages(User user) {
        return null;
    }
}
