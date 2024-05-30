package com.sparta.java_personal_task_3.dto;

import com.sparta.java_personal_task_3.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;

    private String contents;
    private String userId;
//    private Long scheduleId;
    private String date;


    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.userId = comment.getUserId();
//        this.scheduleId = comment.getScheduleId();
        this.date = comment.getDate();
    }

}
