package com.example.bluecodingapp.app.controller;

import com.example.bluecodingapp.app.dto.IdResponseDTO;
import com.example.bluecodingapp.app.dto.user.UserCreateRequestDTO;
import com.example.bluecodingapp.app.dto.user.UserResponseDTO;
import com.example.bluecodingapp.app.dto.user.UserUpdateRequestDTO;
import com.example.bluecodingapp.domain.model.User;
import com.example.bluecodingapp.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<IdResponseDTO> createUser(@Valid @RequestBody UserCreateRequestDTO userCreateRequestDTO){
        User userToInsert = userCreateRequestDTO.toModel();
        User insertedUser = userService.createUser(userToInsert);
        return new ResponseEntity<>(IdResponseDTO.builder().id(insertedUser.getId()).build(), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        return ResponseEntity.ok(UserResponseDTO.modelToDto(userService.getUser(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                      @Valid @RequestBody UserUpdateRequestDTO userUpdateRequestDTO){
       User userToUpdate = userUpdateRequestDTO.toModel(id);
       return ResponseEntity.ok(UserResponseDTO.modelToDto(userService.updateUser(id, userToUpdate)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
