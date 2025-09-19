package com.notes.app.Scribler.Service.Implements;

import com.notes.app.Scribler.DTO.AddUserDto;
import com.notes.app.Scribler.DTO.UserDto;
import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.Repository.UserRepository;
import com.notes.app.Scribler.Service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));
    }

    @Override
    public List<UserDto> getAllUsers(Long tenant_id) {
        User currentUser = getCurrentUser();
        if (!currentUser.getTenant().getId().equals(tenant_id)) {
            throw new IllegalArgumentException("You cannot access users from another tenant");
        }
        List<User> users = userRepository.findAllByTenantId(tenant_id);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users){
            userDtos.add(new UserDto(user));
        }
        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User currentUser = getCurrentUser();
        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist with ID " + userId));
        if (!currentUser.getTenant().getId().equals(targetUser.getTenant().getId())) {
            throw new IllegalArgumentException("You cannot delete a user from another tenant");
        }
        userRepository.delete(targetUser);
    }

    @Override
    public UserDto inviteMember(AddUserDto addUserDto) {
        User currentUser = getCurrentUser();
        User user = new User();
        user.setName(addUserDto.getName());
        user.setEmail(addUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(addUserDto.getPassword()));
        user.setRole(addUserDto.getRole());
        user.setTenant(currentUser.getTenant());
        User newUser = userRepository.save(user);
        return new UserDto(newUser);
    }
}
