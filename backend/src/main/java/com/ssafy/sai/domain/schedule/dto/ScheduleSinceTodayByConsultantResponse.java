package com.ssafy.sai.domain.schedule.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleSinceTodayByConsultantResponse {

    private Long id;

    private LocalDate scheduleDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String category;

    private String detail;

    private Long studentId;
    private String studentName;

    public ScheduleSinceTodayByConsultantResponse(Long id, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String category, String detail, Long studentId, String studentName) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.detail = detail;
        this.studentId = studentId;
        this.studentName = studentName;
    }
}
