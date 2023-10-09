package com.study.alarmpush.service.impl;

import com.study.alarmpush.model.Alarm;
import com.study.alarmpush.model.User;
import com.study.alarmpush.service.PushService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SlackPushServiceImpl implements PushService {

    @Override
    public ResponseEntity<?> createPushMessage(User user, Alarm reqAlarm) {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePushMessage(User user, Alarm reqAlarm) {
        return null;
    }

    @Override
    public ResponseEntity<?> findAllPushMessages(User user) {
        return null;
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
