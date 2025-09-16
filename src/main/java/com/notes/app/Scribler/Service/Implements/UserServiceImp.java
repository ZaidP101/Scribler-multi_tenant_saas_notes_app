package com.notes.app.Scribler.Service.Implements;

import com.notes.app.Scribler.DTO.UserDto;
import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.Repository.UserRepository;
import com.notes.app.Scribler.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers(Long tenant_id) {
       List<User> users = userRepository.findAllBYTenant_Id(tenant_id);
       List<UserDto> userDtos = new ArrayList<>();
       for (User user : users){
           userDtos.add(new UserDto(user));
       }
       return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new IllegalArgumentException("User does not exist with ID "+ userId);
        }
        userRepository.deleteById(userId);
    }
}
