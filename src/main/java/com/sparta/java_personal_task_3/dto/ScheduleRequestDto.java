package com.sparta.java_personal_task_3.dto;

import com.sparta.java_personal_task_3.entity.Schedule;
import com.sparta.java_personal_task_3.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScheduleRequestDto {
    private Long userId;
    private String title;
    private String contents;

    public ScheduleRequestDto(){

    }

    public ScheduleRequestDto(Schedule schedule){
        this.userId = schedule.getUser().getUserId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }
}
