package com.notes.app.Scribler.DTO;

import lombok.Data;

@Data
public class LoginResponseDto {
    String jwt;
    Long userId;
    public LoginResponseDto(String jwt, Long userId) {
        this.jwt = jwt;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
