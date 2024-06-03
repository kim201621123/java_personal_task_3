package service;

import com.sparta.java_personal_task_3.controller.CommentController;
import com.sparta.java_personal_task_3.dto.CommentRequestDto;
import com.sparta.java_personal_task_3.dto.CommentResponseDto;
import com.sparta.java_personal_task_3.dto.ScheduleRequestDto;
import com.sparta.java_personal_task_3.entity.Comment;
import com.sparta.java_personal_task_3.entity.Schedule;
import com.sparta.java_personal_task_3.repository.CommentRepository;
import com.sparta.java_personal_task_3.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    CommentController commentController;


    public ResponseEntity<String> updateComment(Long commentId, ScheduleRequestDto requestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(NullPointerException::new);

        if (commentRepository.existsById(commentId)) {
            comment.update(requestDto);
            return new ResponseEntity<>("성공적으로 수정했습니다. (" + comment.getContents()+")", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment를 찾지 못해 수정하지 못했습니다.", HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(NullPointerException::new);

        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return new ResponseEntity<>("성공적으로 삭제했습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment를 찾지 못해 삭제하지 못했습니다.", HttpStatus.NOT_FOUND);
        }
    }

}
