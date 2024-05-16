package com.sparta.java_personal_task_2.entity;

import com.sparta.java_personal_task_2.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

    public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    private String date;

    public Schedule(String title, String contents, String manager, String password, String date) {
        this.title = title;
        this.contents = contents;
        this.manager = manager;
        this.password = password;
        this.date = date;
    }


    public Schedule(ScheduleRequestDto requestDto) {
    }
}
