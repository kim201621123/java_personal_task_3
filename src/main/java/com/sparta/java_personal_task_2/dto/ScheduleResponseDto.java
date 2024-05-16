package com.sparta.java_personal_task_2.dto;

import com.sparta.java_personal_task_2.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private String title;
    private String contents;
    private String manager;
    private String password;
    private String date;

    public ScheduleResponseDto(){

    }

    public ScheduleResponseDto(Schedule schedule) {
    }
}
