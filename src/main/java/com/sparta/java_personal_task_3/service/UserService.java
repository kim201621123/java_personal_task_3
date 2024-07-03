package com.sparta.java_personal_task_3.service;

import com.sparta.java_personal_task_3.dto.ProfileResponseDto;
import com.sparta.java_personal_task_3.dto.SignUpRequestDto;
import com.sparta.java_personal_task_3.entity.User;
import com.sparta.java_personal_task_3.entity.UserRoleEnum;
import com.sparta.java_personal_task_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ProfileResponseDto signUp(SignUpRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> optionalUser = userRepository.findByUsername(username);

        UserRoleEnum role = UserRoleEnum.USER;
        User user = new User(
                username,
                password,
                requestDto.getIntro(),
                role
        );
        userRepository.save(user);

        return new ProfileResponseDto(user);
    }

    @Transactional
    public void signOut(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.signOut();
    }

    @Transactional
    public void logOut(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.logOut();
    }
}
