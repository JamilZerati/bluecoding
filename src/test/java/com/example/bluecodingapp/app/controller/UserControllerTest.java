package com.example.bluecodingapp.app.controller;

import com.example.bluecodingapp.app.dto.IdResponseDTO;
import com.example.bluecodingapp.app.dto.user.UserCreateRequestDTO;
import com.example.bluecodingapp.app.dto.user.UserResponseDTO;
import com.example.bluecodingapp.domain.model.User;
import com.example.bluecodingapp.domain.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;

    @Test
    public void UserController_CreateUser_ReturnsId(){
        //Arrange
        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                .name("Test")
                .email("Test")
                .age(10)
                .build();
        User insertedUser = User.builder()
                .id(1L).name("Test")
                .email("Teste")
                .age(10)
                .build();
        when(userServiceImpl.createUser(Mockito.any(User.class))).thenReturn(insertedUser);
        //Act
        ResponseEntity<IdResponseDTO> responseEntity = userController.createUser(userCreateRequestDTO);
        //Assert
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertNotNull(responseEntity.getBody().getId());
    }

    @Test
    public void UserController_GetUser_ReturnsUser(){
        User userRetrieved = User.builder().name("Test")
                .email("Teste")
                .age(10)
                .build();
        when(userServiceImpl.getUser(Mockito.anyLong())).thenReturn(userRetrieved);

        ResponseEntity<UserResponseDTO> responseEntity = userController.getUser(1L);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertNotNull(responseEntity.getBody().getName());
        Assertions.assertNotNull(responseEntity.getBody().getEmail());
        Assertions.assertNotNull(responseEntity.getBody().getAge());
    }




}