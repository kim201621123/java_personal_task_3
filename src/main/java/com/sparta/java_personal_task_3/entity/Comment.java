package com.sparta.java_personal_task_3.entity;

import com.sparta.java_personal_task_3.dto.CommentRequestDto;
import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import com.sparta.java_personal_task_3.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;


@Entity
@Getter
@Table(name = "comments")
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY) //연관되는 값이 전부 조회되는거 막기
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) //연관되는 값이 전부 조회되는거 막기
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(String contents, User user, Schedule schedule){
        this.contents = contents;
        this.user = user;
        this.schedule = schedule;
    }

    public Comment() {

    }

    public void update(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }


}
