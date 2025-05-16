package com.example.libraryMangementSystem.services.iservices;


import com.example.libraryMangementSystem.dtos.UserDto;
import com.example.libraryMangementSystem.models.User;
import com.example.libraryMangementSystem.requestDtos.CreateUserRequest;
import com.example.libraryMangementSystem.requestDtos.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);

    User createUser(CreateUserRequest request, boolean isMember);

    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();
}
