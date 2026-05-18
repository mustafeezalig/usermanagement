package com.user.mgmt.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.user.mgmt.entity.User;
import com.user.mgmt.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);
    List<UserResponse> toResponseList(List<User> users);
}