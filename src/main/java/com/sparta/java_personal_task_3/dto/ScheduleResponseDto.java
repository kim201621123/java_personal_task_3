package com.sparta.java_personal_task_3.dto;

import com.sparta.java_personal_task_3.entity.Schedule;
import com.sparta.java_personal_task_3.entity.User;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String contents;



    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUser().getUserId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }


}
