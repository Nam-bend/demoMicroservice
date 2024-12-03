package org.aibles.user_service.features.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.user_service.features.entity.UserEntity;
import org.aibles.user_service.features.repository.UserRepository;
import org.aibles.user_service.features.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        if (userRepository.existsByUsername(userEntity.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        log.info("Creating user: {}", userEntity);
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(String id) {
        log.info("Fetching user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(String id) {
        log.info("Deleting user by id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
}