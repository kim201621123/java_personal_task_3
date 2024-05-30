package com.sparta.java_personal_task_3.repository;

import com.sparta.java_personal_task_3.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT MAX(Schedule .id) FROM Schedule", nativeQuery = true)
    Long findMaxId();
}
