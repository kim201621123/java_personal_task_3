package com.sparta.java_personal_task_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class JavaPersonalTask3Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaPersonalTask3Application.class, args);
    }

}
