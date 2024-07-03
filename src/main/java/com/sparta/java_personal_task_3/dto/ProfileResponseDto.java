package com.sparta.java_personal_task_3.dto;

import com.sparta.java_personal_task_3.entity.User;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ProfileResponseDto {
    private final String username;

    @Getter(value = AccessLevel.PRIVATE)
    private final String password;

    private final String intro;
    private final String role;

    public ProfileResponseDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.intro = user.getIntro();
        this.role = String.valueOf(user.getRole());
    }
}
