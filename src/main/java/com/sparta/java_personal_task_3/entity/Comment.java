package com.sparta.java_personal_task_3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private String userId;
    private Long scheduleId;
    private String date;


    @OneToMany(mappedBy = "comment")
    private List<Writing> commentList = new ArrayList<>();
}
