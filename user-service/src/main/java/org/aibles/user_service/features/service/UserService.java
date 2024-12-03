package org.aibles.user_service.features.service;

import org.aibles.user_service.features.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity userEntity);
    UserEntity getUserById(String id);
    void deleteUser(String id);
    List<UserEntity> getAllUsers();
}
