package com.example.bluecodingapp.infrastructure.repository.user;

import com.example.bluecodingapp.infrastructure.repository.user.entity.UserJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends CrudRepository<UserJpaEntity, Long> {
}
