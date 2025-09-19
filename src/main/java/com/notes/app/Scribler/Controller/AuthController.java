package com.notes.app.Scribler.Controller;

import com.notes.app.Scribler.DTO.LoginRequestDto;
import com.notes.app.Scribler.DTO.LoginResponseDto;
import com.notes.app.Scribler.DTO.SignupRequestDto;
import com.notes.app.Scribler.DTO.SignupResponseDto;
import com.notes.app.Scribler.Service.Security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto);
    }

    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto signupRequestDto){
        return authService.signup(signupRequestDto);
    }
}
