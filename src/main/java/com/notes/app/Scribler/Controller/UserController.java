package com.notes.app.Scribler.Controller;

import com.notes.app.Scribler.DTO.AddUserDto;
import com.notes.app.Scribler.DTO.UserDto;
import com.notes.app.Scribler.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("#tenant_id == authentication.principal.id")
    @GetMapping("/allUsers/tenant/{tenant_id}")
    public List<UserDto> getAllUsers(@PathVariable Long tenant_id){
        return userService.getAllUsers(tenant_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{user_id}")
    public void deleteUser(@PathVariable Long user_id){
        userService.deleteUser(user_id);
    }

    // invite a user by admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/invite")
    public UserDto inviteMember(@RequestBody AddUserDto addUserDto){
        UserDto userDto = userService.inviteMember(addUserDto);
        return userDto;
    }
}
