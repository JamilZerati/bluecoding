package com.example.bluecodingapp.infrastructure.repository.user;

import com.example.bluecodingapp.domain.exception.UserNotFoundException;
import com.example.bluecodingapp.domain.model.User;
import com.example.bluecodingapp.domain.repository.UserRepository;
import com.example.bluecodingapp.infrastructure.repository.user.entity.UserJpaEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User saveUser(User userToInsert) {
        UserJpaEntity userInserted = userJpaRepository.save(UserJpaEntity.modelToEntity(userToInsert));
        return userInserted.entityToModel();

    }

    @Override
    public User findById(Long id) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userJpaEntity.entityToModel();
    }

    @Override
    public void deleteUser(Long id) {
        userJpaRepository.deleteById(id);
    }
}
