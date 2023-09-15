package com.example.bluecodingapp.domain.service.impl;

import com.example.bluecodingapp.domain.exception.UserNotFoundException;
import com.example.bluecodingapp.domain.model.User;
import com.example.bluecodingapp.infrastructure.repository.user.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepositoryImpl userRepositoryImpl;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void UserServiceImpl_CreateUser_ReturnsCreatedUser(){
        User user = User.builder().name("Teste")
                .email("Teste")
                .age(1)
                .build();
        when(userRepositoryImpl.saveUser(Mockito.any(User.class))).thenReturn(user);
        User createdUser = userServiceImpl.createUser(user);
        Assertions.assertNotNull(createdUser);
    }

    //Todo Create with invalid info

    @Test
    public void UserServiceImpl_updateUser_ReturnsUpdatedUser(){
        User userToBeUpdated = User.builder().name("Test").email("Test").age(1).build();
        User userUpdated = User.builder().name("Test Updated")
                .email("Test Updated email")
                .age(10)
                .build();
        when(userRepositoryImpl.findById(Mockito.anyLong())).thenReturn(userToBeUpdated);
        when(userRepositoryImpl.saveUser(Mockito.any(User.class))).thenReturn(userUpdated);
        Assertions.assertEquals(userUpdated.getName(),
                userServiceImpl.updateUser(1L, userUpdated).getName());
        Assertions.assertEquals(userUpdated.getAge(),
                userServiceImpl.updateUser(1L, userUpdated).getAge());
    }

    //Todo update with invalid info

    @Test
    public void UserServiceImpl_updateUser_UserNotFound(){
        User userWithUpdatedInfo = User.builder().name("Test").email("Test").age(1).build();
        when(userRepositoryImpl.findById(Mockito.anyLong())).thenReturn(null);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userServiceImpl.updateUser(1L, userWithUpdatedInfo);
        });
    }

    @Test
    public void UserServiceImpl_updateUserWithoutAnyInfo(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.updateUser(1L, User.builder().build());
        });
    }

    @Test
    public void UserServiceImpl_deleteUser_UserDeleted(){
        when(userRepositoryImpl.findById(Mockito.anyLong())).thenReturn(User.builder().build());
        userServiceImpl.deleteUser(1L);
        Mockito.verify(userRepositoryImpl, Mockito.times(1)).deleteUser(Mockito.anyLong());
    }


}