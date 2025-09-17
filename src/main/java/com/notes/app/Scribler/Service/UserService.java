package com.notes.app.Scribler.Service;

import com.notes.app.Scribler.DTO.AddUserDto;
import com.notes.app.Scribler.DTO.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers(Long tenantId);

    void deleteUser(Long userId);

    UserDto inviteMember(AddUserDto addUserDto);
}
