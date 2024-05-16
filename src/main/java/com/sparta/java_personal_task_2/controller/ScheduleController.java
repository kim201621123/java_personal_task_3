package com.sparta.java_personal_task_2.controller;

import com.sparta.java_personal_task_2.dto.ScheduleRequestDto;
import com.sparta.java_personal_task_2.dto.ScheduleResponseDto;
import com.sparta.java_personal_task_2.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping("/create")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){

        Schedule schedule = new Schedule(requestDto);

        // RequestDto > Entity
        Long maxID = scheduleList.isEmpty() ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setId(maxID);

        scheduleList.put(schedule.getId(), schedule);

        // Entity > ResponseDto 변환
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;

    }
}
