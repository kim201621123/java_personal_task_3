package com.sparta.java_personal_task_3.controller;

import com.sparta.java_personal_task_3.dto.CommentRequestDto;
import com.sparta.java_personal_task_3.dto.CommentResponseDto;
import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import com.sparta.java_personal_task_3.dto.ScheduleResponseDto;
import com.sparta.java_personal_task_3.entity.Comment;
import com.sparta.java_personal_task_3.entity.Schedule;
import com.sparta.java_personal_task_3.repository.CommentRepository;
import com.sparta.java_personal_task_3.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    private final Map<Long, Comment> commentList = new HashMap<>();

    @PostMapping("/create")
    public CommentResponseDto createCommentDto(@RequestBody CommentRequestDto requestDto,
                                               @RequestParam Long scheduleId){
        //schedule의 id를 parameter로 받아오려고 하는데 더 좋은 방법이 있을까?

        Long maxID = findMaxIdOfCommentTable();

        requestDto.setId(maxID++); //자동으로 올라감
        Comment comment = new Comment(requestDto);

        // RequestDto > Entity
        //Long maxID = commentList.size() > 0 ? Collections.max(commentList.keySet()) + 1 : 1;
        comment.setId(maxID);

        commentList.put(comment.getId(), comment);

        //입력받은 키로 schedule 찾아오기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);

        comment.setSchedule(schedule); // 왜래키 설정

        scheduleRepository.save(schedule);
        commentRepository.save(comment);

        // Entity > ResponseDto 변환
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
        return commentResponseDto;
    }


    @PutMapping("/update")
    public Comment updateComment(@RequestParam Long scheduleId, @RequestParam Long commentId,
                                   @RequestBody ScheduleRequestDto requestDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(NullPointerException::new);
        comment.update(requestDto);

        return comment;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam Long scheduleId, @RequestParam Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NullPointerException::new);

        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return new ResponseEntity<>("성공적으로 삭제했습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment를 찾지 못해 삭제하지 못했습니다.", HttpStatus.NOT_FOUND);
        }
    }


    public Long findMaxIdOfCommentTable(){
        return commentRepository.findMaxId();
    }


}
