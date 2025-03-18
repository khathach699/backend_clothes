package com.example.backend_clothes.mapper;

import com.example.backend_clothes.dto.request.UserCreateRequest;
import com.example.backend_clothes.dto.request.UserUpdateRequest;
import com.example.backend_clothes.dto.response.UserResponse;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    User toUser(UserCreateRequest request);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    UserResponse toUserResponse(User user);


    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}