package com.example.bluecodingapp.app.dto.user;

import com.example.bluecodingapp.domain.model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private String name;
    private String email;
    private Integer age;

    public static UserResponseDTO modelToDto(User user) {
        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }
}
