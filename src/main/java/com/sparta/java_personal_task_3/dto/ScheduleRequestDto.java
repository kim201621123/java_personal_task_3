package com.sparta.java_personal_task_3.dto;

import com.sparta.java_personal_task_3.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScheduleRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    private String date;

    public ScheduleRequestDto(){

    }

    public ScheduleRequestDto(Schedule schedule){
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        this.date = schedule.getDate();
    }
}
