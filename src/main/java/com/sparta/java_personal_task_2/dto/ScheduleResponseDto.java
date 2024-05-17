package com.sparta.java_personal_task_2.dto;

import com.sparta.java_personal_task_2.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    private String date;

    public ScheduleResponseDto(){

    }

    // password는 조회되지 않으므로 get메서드가 없음
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        this.date = schedule.getDate();
    }
}
