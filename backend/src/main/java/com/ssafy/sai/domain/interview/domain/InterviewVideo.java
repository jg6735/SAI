package com.ssafy.sai.domain.interview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.sai.global.common.BaseEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Setter
public class InterviewVideo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_video_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "interview_info_id")
    private InterviewInfo interviewInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_interview_question_id")
    private UsedInterviewQuestion usedInterviewQuestion;

    private String videoUrl;

    private String videoName;

    private String audioUrl;

    private String audioName;

    private String feedback;

    @Column(length = 4000)
    private String stt;

    private Long wrongPostureCount;

    private Double emotionRatio;

    public void createFeedback(String feedback) {
        this.feedback = feedback;
    }
}
