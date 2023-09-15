package com.example.bluecodingapp.domain.service;

import com.example.bluecodingapp.app.dto.user.UserResponseDTO;
import com.example.bluecodingapp.domain.model.User;

public interface UserService {
    User createUser(User userToInsert);

    User getUser(Long id);

    User updateUser(Long id, User userToUpdate);

    void deleteUser(Long id);
}
