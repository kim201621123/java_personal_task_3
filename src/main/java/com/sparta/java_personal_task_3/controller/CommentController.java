package com.sparta.java_personal_task_3.controller;

import com.sparta.java_personal_task_3.dto.CommentRequestDto;
import com.sparta.java_personal_task_3.dto.CommentResponseDto;
import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import com.sparta.java_personal_task_3.entity.Comment;
import com.sparta.java_personal_task_3.entity.Schedule;
import com.sparta.java_personal_task_3.repository.CommentRepository;
import com.sparta.java_personal_task_3.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.sparta.java_personal_task_3.service.CommentService;

import java.util.HashMap;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @PostMapping("/create/{scheduleId}")
    public CommentResponseDto createCommentDto(@RequestBody CommentRequestDto requestDto,
                                               @PathVariable Long scheduleId){

        CommentResponseDto commentResponseDto = commentService.createComment(requestDto, scheduleId);
        return commentResponseDto;

    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<String> updateComment(@RequestBody ScheduleRequestDto requestDto,
                                                @PathVariable Long commentId) {
        return commentService.updateComment(commentId, requestDto);

    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }



}
