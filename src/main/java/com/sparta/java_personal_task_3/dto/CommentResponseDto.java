package com.sparta.java_personal_task_3.dto;

import com.sparta.java_personal_task_3.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String contents;
    private Long userId;
    private Long scheduleId;


    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.userId = comment.getUser().getUserId();
        this.scheduleId = comment.getSchedule().getId();
    }

}
