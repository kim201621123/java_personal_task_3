package com.sparta.java_personal_task_3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Length(min = 4, max = 10)
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String intro;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    private String refreshToken;

    public User(String username, String password, String intro, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.intro = intro;
        this.role = role;
    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void signOut() {
        this.role = UserRoleEnum.WITHDRAW;
        this.refreshToken = null;
    }
    public void logOut() {
        refreshToken = null;
    }
}
