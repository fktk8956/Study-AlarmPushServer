package com.study.alarmpush.service;

import com.study.alarmpush.model.Alarm;
import com.study.alarmpush.model.User;
import org.springframework.http.ResponseEntity;

public interface PushService {
    public ResponseEntity<?> createPushMessage(User user, Alarm reqAlarm);

    public ResponseEntity<?> updatePushMessage(User user, Alarm reqAlarm);

    // public ResponseEntity<?> findPushMessage(User user, );

    public ResponseEntity<?> findAllPushMessages(User user);

    public ResponseEntity<?> deletePushMessage(User user, Alarm reqAlarm);

    public ResponseEntity<?> deleteAllPushMessages(User user);
}