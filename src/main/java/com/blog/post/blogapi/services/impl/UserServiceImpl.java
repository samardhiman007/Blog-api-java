package com.blog.post.blogapi.services.impl;

import com.blog.post.blogapi.entities.User;
import com.blog.post.blogapi.exceptions.ResourceNotFoundException;
import com.blog.post.blogapi.payloads.UserDto;
import com.blog.post.blogapi.repositories.UserRepo;
import com.blog.post.blogapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=new User();
        user=this.dtoToUser(userDto);
        this.userRepo.save(user);

        return this.userToDto(user);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.get(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User" ,"id",userId));

       // user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updateUser=this.userRepo.save(user);

        return this.userToDto(updateUser);
    }

    // Util method for using orElseThrow
    public Optional<User> get(Integer userId){
        return this.userRepo.findById(userId);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.get(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User" ,"id",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList=this.userRepo.findAll();
        List<UserDto> userDtoList=userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deteleUser(Integer userId) {
        User user = this.get(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User" ,"id",userId));

        this.userRepo.deleteById(userId);
    }
    private User dtoToUser(UserDto userDto){
        User user= this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        return user;
    }

    private UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());

        return userDto;
    }


}
