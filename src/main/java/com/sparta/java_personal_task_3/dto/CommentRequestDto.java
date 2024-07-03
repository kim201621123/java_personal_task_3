package com.sparta.java_personal_task_3.dto;

import com.sparta.java_personal_task_3.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String contents;
    private Long userId;
    private Long scheduleId;

    public CommentRequestDto(Comment comment){
        this.contents = comment.getContents();
        this.userId = comment.getUser().getUserId();
        this.scheduleId = comment.getSchedule().getId();
    }
}
