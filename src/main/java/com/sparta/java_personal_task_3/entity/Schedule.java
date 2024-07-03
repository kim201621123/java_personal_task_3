package com.sparta.java_personal_task_3.entity;

import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //연관되는 값이 전부 조회되는거 막기
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Schedule(ScheduleRequestDto requestDto, User user) {
        this.user = user;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    // 제목, 내용, 담담자만 수정됨
    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }


}
