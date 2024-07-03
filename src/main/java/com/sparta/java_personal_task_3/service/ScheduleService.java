package com.sparta.java_personal_task_3.service;

import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import com.sparta.java_personal_task_3.dto.ScheduleResponseDto;
import com.sparta.java_personal_task_3.entity.Schedule;
import com.sparta.java_personal_task_3.entity.User;
import com.sparta.java_personal_task_3.repository.ScheduleRepository;
import com.sparta.java_personal_task_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    @Autowired
    private final ScheduleRepository scheduleRepository;
    @Autowired
    private final UserRepository userRepository;

    public ScheduleResponseDto creteSchedule(ScheduleRequestDto scheduleRequestDto, Long userId) {
        System.out.println("조회 전");
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        System.out.println("조회 완료");
        Schedule schedule = new Schedule(scheduleRequestDto, user);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto updateSchedule(ScheduleRequestDto scheduleRequestDto, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
        schedule.update(scheduleRequestDto);
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
        scheduleRepository.deleteById(scheduleId);
        return new ScheduleResponseDto(schedule);
    }
}
