package com.study.alarmpush.service.impl;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.common.ResultEnum;
import com.study.alarmpush.model.*;
import com.study.alarmpush.repository.AlarmRepository;
import com.study.alarmpush.service.PushService;
import com.study.alarmpush.service.UserManagerService;
import com.study.alarmpush.util.SlackMessengerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class SlackPushServiceImpl implements PushService {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private SlackMessengerUtil messengerUtil;

    @Override
    @Transactional
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
    public ResponseEntity<?> updatePushMessage(Long id, UpdateAlarmRequestVO requestVO) throws ResourceNotFoundException {
        Map<String, String> ret = new HashMap<>();
        HttpStatus accepted;

        try {
            Optional<Alarm> findAlarmOptional = Optional.ofNullable(alarmRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " is not founded")));

            if (findAlarmOptional.isPresent()) {
                Alarm alarm = findAlarmOptional.get();
                alarm.setMessage(requestVO.getMessage());
                alarmRepository.save(alarm);
                ret.put("result", ResultEnum.SUCCESS.getResultString());
                accepted = HttpStatus.ACCEPTED;
            } else {
                ret.put("result", ResultEnum.FAIL.getResultString());
                accepted = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (ResourceNotFoundException | RuntimeException re) {
            log.error(re.getMessage());
            throw re;
        }

        return new ResponseEntity<>(ret, accepted);
    }

    @Override
    public ResponseEntity<?> deletePushMessage(Long id) {
        try {
            alarmRepository.deleteById(id);
        } catch (RuntimeException re) {
            log.error(re.getMessage());
            throw re;
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> deleteAllPushMessages(DeleteAllAlarmsRequestVO requestVO) throws ResourceNotFoundException {
        try {
            Optional<User> userOptional = userManagerService.findUserByEmail(requestVO.getEmail());
            User findUser = userOptional.get();
            findUser.getAlarms().forEach(alarm -> alarmRepository.deleteById(alarm.getId()));
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw exception;
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
