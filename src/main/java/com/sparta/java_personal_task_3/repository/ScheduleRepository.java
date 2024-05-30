package com.sparta.java_personal_task_3.repository;

import com.sparta.java_personal_task_3.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
