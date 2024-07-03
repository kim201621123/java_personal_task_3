package com.sparta.java_personal_task_3.controller;

import com.sparta.java_personal_task_3.dto.ProfileResponseDto;
import com.sparta.java_personal_task_3.dto.SignUpRequestDto;
import com.sparta.java_personal_task_3.security.UserDetailsImpl;
import com.sparta.java_personal_task_3.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("sign-up")
    public ProfileResponseDto signUp(
            @Valid @RequestBody SignUpRequestDto requestDto) {

        return userService.signUp(requestDto);
    }

    @DeleteMapping("/sign-out")
    public ResponseEntity<String> signOut(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        userService.signOut(userDetails.getUser().getUserId());

        return ResponseEntity.ok().body("회원 탈퇴에 성공했습니다.");
    }

    @DeleteMapping("/log-out")
    public ResponseEntity<String> logOut(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        userService.logOut(userDetails.getUser().getUserId());

        return ResponseEntity.ok().body("로그아웃 성공하셨습니다");
    }
}
