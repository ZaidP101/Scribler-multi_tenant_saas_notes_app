package com.notes.app.Scribler.Service.Security;

import com.notes.app.Scribler.DTO.LoginRequestDto;
import com.notes.app.Scribler.DTO.LoginResponseDto;
import com.notes.app.Scribler.DTO.SignupRequestDto;
import com.notes.app.Scribler.DTO.SignupResponseDto;
import com.notes.app.Scribler.Entity.Tenant;
import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.Repository.TenantRepository;
import com.notes.app.Scribler.Repository.UserRepository;
import com.notes.app.Scribler.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TenantRepository tenantRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder, TenantRepository tenantRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tenantRepository = tenantRepository;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        if (userRepository.findByEmail(signupRequestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        User user = new User();
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        user.setRole(Role.MEMBER);
        Tenant tenant = tenantRepository.findById(signupRequestDto.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        user.setTenant(tenant);
        User savedUser = userRepository.save(user);
        return new SignupResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new IllegalStateException("No authenticated user found");
        }
        return (User) authentication.getPrincipal();
    }

    public static Long getCurrentTenantId() {
        return getCurrentUser().getTenant().getId();
    }
}
