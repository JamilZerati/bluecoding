package com.example.bluecodingapp.infrastructure.repository.user.entity;

import com.example.bluecodingapp.domain.model.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private Integer age;

    public static UserJpaEntity modelToEntity(User user) {
        return UserJpaEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }

    public User entityToModel() {
        return User.builder()
                .id(this.getId())
                .name(this.getName())
                .email(this.getEmail())
                .age(this.getAge())
                .build();
    }
}
