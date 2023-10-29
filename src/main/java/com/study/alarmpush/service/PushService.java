package com.study.alarmpush.service;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.model.Alarm;
import com.study.alarmpush.model.CreateAlarmRequestVO;
import com.study.alarmpush.model.UpdateAlarmRequestVO;
import com.study.alarmpush.model.User;
import org.springframework.http.ResponseEntity;

public interface PushService {
    public ResponseEntity<?> createPushMessage(CreateAlarmRequestVO requestVO) throws ResourceNotFoundException;

    public ResponseEntity<?> updatePushMessage(Long id, UpdateAlarmRequestVO requestVO);

    // public ResponseEntity<?> findPushMessage(User user, );

    public ResponseEntity<?> deletePushMessage(User user, Alarm reqAlarm);

    public ResponseEntity<?> deleteAllPushMessages(User user);
}