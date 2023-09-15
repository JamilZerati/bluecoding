package com.example.bluecodingapp.domain.repository;

import com.example.bluecodingapp.domain.model.User;

public interface UserRepository {
    User saveUser(User userToInsert);

    User findById(Long id);

    void deleteUser(Long id);
}
