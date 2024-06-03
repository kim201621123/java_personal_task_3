package com.sparta.java_personal_task_3.entity;

import com.sparta.java_personal_task_3.dto.CommentRequestDto;
import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;


@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    private String userId;
//    private Long scheduleId;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY) //연관되는 값이 전부 조회되는거 막기
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto){
        this.id = requestDto.getId();
        this.contents = requestDto.getContents();
        this.userId = requestDto.getUserId();
//this.scheduleId = requestDto.getScheduleId();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public Comment() {

    }

}
