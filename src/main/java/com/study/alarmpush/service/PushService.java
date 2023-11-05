package com.study.alarmpush.service;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.model.*;
import org.springframework.http.ResponseEntity;

public interface PushService {
    public ResponseEntity<?> createPushMessage(CreateAlarmRequestVO requestVO) throws ResourceNotFoundException;

    public ResponseEntity<?> updatePushMessage(Long id, UpdateAlarmRequestVO requestVO) throws ResourceNotFoundException;

    // public ResponseEntity<?> findPushMessage(User user, );

    public ResponseEntity<?> deletePushMessage(Long id);

    public ResponseEntity<?> deleteAllPushMessages(DeleteAllAlarmsRequestVO requestVO) throws ResourceNotFoundException;
}