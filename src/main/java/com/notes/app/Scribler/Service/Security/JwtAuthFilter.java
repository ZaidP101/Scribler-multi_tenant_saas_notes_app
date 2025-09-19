package com.notes.app.Scribler.Service.Security;

import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j // for logs
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    public final JwtService jwtService;

    public JwtAuthFilter(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String reqTokenHeader = request.getHeader("Authorization");
        if(reqTokenHeader == null || !reqTokenHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = reqTokenHeader.substring(7).trim();
        //String name = jwtService.getUserFromToken(token);
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtService.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String name = claims.getSubject();
            String role = claims.get("role", String.class);

            if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByEmail(name).orElseThrow();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                System.out.println("User Authorities: " + user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }catch (Exception e) {
            // Handle token validation errors
        }
        filterChain.doFilter(request,response);
    }
}
