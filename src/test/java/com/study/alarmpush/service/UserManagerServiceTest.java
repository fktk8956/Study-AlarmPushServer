package com.study.alarmpush.service;

import com.study.alarmpush.common.ResourceNotFoundException;
import com.study.alarmpush.model.AddUserRequestVO;
import com.study.alarmpush.model.LoginUserRequestVO;
import com.study.alarmpush.model.User;
import com.study.alarmpush.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Mockito 확장 사용
class UserManagerServiceTest {
    @InjectMocks
    private UserManagerService userManagerService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        //userManagerService = new UserManagerService(userRepository);
    }

    @Test
    void 회원가입_이미_가입유저() {
        // given
        AddUserRequestVO reqUser = new AddUserRequestVO();
        reqUser.setEmail("dong1");
        reqUser.setNickName("dong1");
        reqUser.setPassword("dong1");

        User findUser = new User();
        findUser.setNickName("dong1");

        // when
        when(userRepository.findByLoginEmail(reqUser.getEmail())).thenReturn(Optional.of(findUser));

        // then
        ResponseEntity<?> response = userManagerService.registerUser(reqUser);
        assertEquals("email is already registered", response.getBody());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void 회원가입_성공() {
        // given
        AddUserRequestVO reqUser = new AddUserRequestVO();
        reqUser.setEmail("dong1");
        reqUser.setNickName("dong1");
        reqUser.setPassword("dong1");

        // when
        when(userRepository.findByLoginEmail(reqUser.getEmail())).thenReturn(Optional.empty());

        // then
        ResponseEntity<?> response = userManagerService.registerUser(reqUser);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void 로그인_실패() throws ResourceNotFoundException {
        // given
        LoginUserRequestVO loginUserRequestVO = new LoginUserRequestVO();
        loginUserRequestVO.setEmail("dong1");
        loginUserRequestVO.setPassword("dong1");

        // when
        when(userRepository.findByLoginEmailAndLoginPassword("dong1", "dong1")).thenReturn(Optional.empty());

        // then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userManagerService.loginUser(loginUserRequestVO);
        });
        assertEquals(loginUserRequestVO.getEmail() + " is not founded", exception.getMessage());
        //assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void 로그인_성공() throws ResourceNotFoundException {
        // given
        LoginUserRequestVO loginUserRequestVO = new LoginUserRequestVO();
        loginUserRequestVO.setEmail("dong1");
        loginUserRequestVO.setPassword("dong1");

        User findUser = new User();
        findUser.setNickName("dong1");

        // when
        when(userRepository.findByLoginEmailAndLoginPassword("dong1", "dong1")).thenReturn(Optional.of(findUser));

        // then
        ResponseEntity<?> response = userManagerService.loginUser(loginUserRequestVO);
        Map<String, String> resMap = (Map<String, String>) response.getBody();
        assertEquals(resMap.get("nickName"), "dong1");
        assertEquals(resMap.get("result"), "SUCCESS");
        assertEquals(resMap.get("token"), "test");
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}