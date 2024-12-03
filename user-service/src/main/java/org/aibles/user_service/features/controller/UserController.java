package org.aibles.user_service.features.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.user_service.features.entity.UserEntity;
import org.aibles.user_service.features.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        log.info("Creating user: {}", userEntity);
        return userService.createUser(userEntity);
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable String id) {
        log.info("Fetching user by id: {}", id);
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        log.info("Deleting user by id: {}", id);
        userService.deleteUser(id);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        log.info("Fetching all users");
        return userService.getAllUsers();
    }
}

