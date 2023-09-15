package com.example.bluecodingapp.domain.service.impl;

import com.example.bluecodingapp.domain.exception.UserNotFoundException;
import com.example.bluecodingapp.domain.model.User;
import com.example.bluecodingapp.domain.repository.UserRepository;
import com.example.bluecodingapp.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User userToInsert) {
        return userRepository.saveUser(userToInsert);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User userToUpdate) {
        validateUserToUpdate(userToUpdate);
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            if (userToUpdate.getName() != null)
                existingUser.setName(userToUpdate.getName());
            if (userToUpdate.getEmail() != null)
                existingUser.setEmail(userToUpdate.getEmail());
            if (userToUpdate.getAge() != null)
                existingUser.setAge(userToUpdate.getAge());
            return userRepository.saveUser(existingUser);
        }
        throw new UserNotFoundException("User not found");
    }

    private void validateUserToUpdate(User userToUpdate) {
        if (userToUpdate.getName() == null && userToUpdate.getEmail() == null && userToUpdate.getAge() == null)
            throw new IllegalArgumentException("At least one field must be filled");
        if (userToUpdate.getAge() != null && userToUpdate.getAge() < 0)
            throw new IllegalArgumentException("Age must be greater than 0");
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null)
            userRepository.deleteUser(id);
        else
            throw new UserNotFoundException("User not found");
    }


}
