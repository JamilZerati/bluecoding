package com.example.bluecodingapp.app.dto.user;

import com.example.bluecodingapp.domain.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequestDTO {
    private String name;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Min(value = 1, message = "Age must be greater than 0")
    private Integer age;

    public User toModel(Long id) {
        return User.builder()
                .id(id)
                .name(this.getName())
                .email(this.getEmail())
                .age(this.getAge())
                .build();
    }
}
