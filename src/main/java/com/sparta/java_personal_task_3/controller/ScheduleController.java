package com.sparta.java_personal_task_3.controller;

import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import com.sparta.java_personal_task_3.dto.ScheduleResponseDto;
import com.sparta.java_personal_task_3.entity.Schedule;
import com.sparta.java_personal_task_3.repository.CommentRepository;
import com.sparta.java_personal_task_3.repository.ScheduleRepository;
import com.sparta.java_personal_task_3.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Transactional
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;


    // 일정 DTO 생성
    @PostMapping("/create")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.creteSchedule(scheduleRequestDto, scheduleRequestDto.getUserId());
        return scheduleResponseDto;
    }

//    @GetMapping("/get")
//    public List<ScheduleResponseDto> getSchedules() {
//        List<ScheduleResponseDto> scheduleResponseList;
//        scheduleResponseList = scheduleRepository.findAllById();
//
//        return responseList;
//    }

    // 일정 DTO 사용해 특정 Schedule 반환
    @GetMapping("/getOne/{scheduleId}")
    public ScheduleResponseDto getSelectedSchedule(@PathVariable Long ScheduleId) {

        return new ScheduleResponseDto(scheduleRepository.findById(ScheduleId).orElseThrow());
    }

    @PutMapping("/update/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto, @PathVariable Long scheduleId) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(scheduleRequestDto, scheduleId);
        return scheduleResponseDto;
    }

    @DeleteMapping("/delete/{scheduleId}")
    public String deleteSchedule(@PathVariable Long scheduleId) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.deleteSchedule(scheduleId);
        return "삭제 완료";
    }
}
