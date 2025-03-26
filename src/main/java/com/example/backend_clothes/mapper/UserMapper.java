package com.example.backend_clothes.mapper;

import com.example.backend_clothes.dto.request.UserCreateRequest;
import com.example.backend_clothes.dto.request.UserUpdateRequest;
import com.example.backend_clothes.dto.response.UserResponse;
import com.example.backend_clothes.dto.response.UserUpdateResponse;
import com.example.backend_clothes.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreateRequest request);

    @Mapping(source = "id", target = "userId")
    UserResponse toUserResponse(User user);

    @Mapping(source = "id", target = "userId")
    UserUpdateResponse toUserUpdateResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}