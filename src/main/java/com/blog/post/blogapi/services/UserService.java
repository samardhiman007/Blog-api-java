package com.blog.post.blogapi.services;

import com.blog.post.blogapi.entities.User;
import com.blog.post.blogapi.payloads.UserDto;

import java.util.List;

public interface UserService {
UserDto createUser(UserDto userDto);
UserDto updateUser(UserDto userDto,Integer userId);
UserDto getUserById(Integer userId);
List<UserDto> getAllUsers();
void deteleUser(Integer userId);
}
