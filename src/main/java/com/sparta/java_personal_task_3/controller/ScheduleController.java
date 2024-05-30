package com.sparta.java_personal_task_3.controller;

import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import com.sparta.java_personal_task_3.dto.ScheduleResponseDto;
import com.sparta.java_personal_task_3.entity.Schedule;
import com.sparta.java_personal_task_3.repository.CommentRepository;
import com.sparta.java_personal_task_3.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Transactional
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;


    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    // 일정 DTO 생성
    @PostMapping("/create")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        //System.out.println(findMaxIdOfScheduleTable() +" 찾아라 비밀의 열쇠");
        Long maxID = findMaxIdOfScheduleTable();

        requestDto.setId(maxID++);
        Schedule schedule = new Schedule(requestDto);

        // RequestDto > Entity
        schedule.setId(maxID);

        scheduleList.put(schedule.getId(), schedule);

        scheduleRepository.save(schedule);

        // Entity > ResponseDto 변환
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    // 일정 DTO 사용해 전체 schedule List 반환
    @GetMapping("/read")
    public List<ScheduleResponseDto> getSchedules() {
        // Map to List
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        responseList = responseList.stream().sorted(Comparator.comparing(ScheduleResponseDto ::getDate).reversed()).toList();

        return responseList;
    }

    // 일정 DTO 사용해 특정 Schedule 반환
    @GetMapping("/readOne")
    public Schedule getSelectedSchedule(@RequestParam Long id) {

        Schedule schedule = new Schedule();
        for (Schedule scheduleFind : scheduleList.values()) {
            if (Objects.equals(scheduleFind.getId(), id)) {
                schedule = scheduleFind;
                // 비밀번호가 조회되지 않아야 함
                schedule.setPassword(null);
            } else {
                throw new IllegalArgumentException("찾는 schedule은 존재하지 않습니다.");
            }
        }
        return schedule;

    }


    //     입력받은 값으로 update 하고 수정된 Schedule 객체 반환
//     password도 입력받고 맞으면 로직 실행
//     비밀번호 입력받는건 새로운 메서드로 분리하는게 맞는 것 같지만 구현을 어떻게 할 지 모르겠음
    @PutMapping("/update")
    public Schedule updateSchedule(@RequestParam String password, @RequestParam Long id, @RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule();
        for (Schedule scheduleFind : scheduleList.values()) {
            if (password.equals(scheduleFind.getPassword())) {
                if (scheduleFind.getId().equals(id)) {
                    // 제목, 내용, 담당자만 바뀜
                    scheduleFind.update(requestDto);
                    schedule = scheduleFind;
                    // 비밀번호가 조회되지 않아야 함
                    schedule.setPassword(null);
                    return schedule;
                } else {
                    throw new IllegalArgumentException("찾는 schedule은 존재하지 않습니다.");
                }
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }
        throw new IllegalArgumentException("다시 시도해 주세요");
    }


    //pahtvariable로 받는게 맞는 것 같아!!
    //사실 비밀번호를 노출시키는게 마음에 들진 않는다...
    @DeleteMapping("/delete")
    public Schedule deleteSchedule(@RequestParam String password, @RequestParam Long id) {
        Schedule schedule = new Schedule();
        for (Schedule scheduleFind : scheduleList.values()) {
            if (password.equals(scheduleFind.getPassword())) {
                if (scheduleFind.getId().equals(id)) {
                    // 삭제
                    scheduleList.remove(scheduleFind.getId());
                    schedule = scheduleFind;
                    // 비밀번호가 조회되지 않아야 함
                    schedule.setPassword(null);
                    return schedule;
                } else {
                    throw new IllegalArgumentException("찾는 schedule은 존재하지 않습니다.");
                }
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }
        throw new IllegalArgumentException("다시 시도해 주세요");

    }

    public Long findMaxIdOfScheduleTable(){
        return scheduleRepository.findMaxId();
    }

}
