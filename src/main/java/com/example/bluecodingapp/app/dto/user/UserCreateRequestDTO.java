package com.example.bluecodingapp.app.dto.user;

import com.example.bluecodingapp.domain.model.User;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Min(value = 1, message = "Age must be greater than 0")
    @NotNull(message = "Age is mandatory")
    private Integer age;

    public User toModel() {
        return User.builder()
                .name(this.getName())
                .email(this.getEmail())
                .age(this.getAge())
                .build();
    }
}
