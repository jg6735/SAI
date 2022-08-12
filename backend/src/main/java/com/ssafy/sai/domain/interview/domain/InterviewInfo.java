package com.ssafy.sai.domain.interview.domain;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.schedule.domain.Schedule;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class InterviewInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saved_interview_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_student_id")
    private Member memberStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_consultant_id")
    private Member memberConsultant;

    @Column(name ="feedback_request")
    @Enumerated(EnumType.STRING)
    private FeedbackRequestStatus feedbackRequestStatus;

    @Column(name ="feedback_complete")
    @Enumerated(EnumType.STRING)
    private FeedbackCompleteStatus feedbackCompleteStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="interview_date")
    private LocalDate interviewDate;

    private String category;
    private String detail;



}
