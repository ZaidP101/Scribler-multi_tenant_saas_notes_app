package com.notes.app.Scribler.Controller;

import com.notes.app.Scribler.DTO.UserDto;
import com.notes.app.Scribler.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers/tenant/{tenant_id}")
    public List<UserDto> getAllUsers(@PathVariable Long tenant_id){
        return userService.getAllUsers(tenant_id);
    }

    @DeleteMapping("/delete/{user_id}")
    public void deleteUser(@PathVariable Long user_id){
        userService.deleteUser(user_id);
    }

}
