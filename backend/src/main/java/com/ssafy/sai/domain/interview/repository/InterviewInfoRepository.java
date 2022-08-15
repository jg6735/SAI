package com.ssafy.sai.domain.interview.repository;

import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterviewInfoRepository extends JpaRepository<InterviewInfo, Long> {

    @Query("select i from InterviewInfo i where i.feedbackRequestStatus = 'true' " +
            " and i.feedbackCompleteStatus = 'false' and i.memberConsultant.id = :id")
    List<InterviewInfo> findInterviewInfoByFeedbackRequest(@Param("id") Long id);

    @Query("select i from InterviewInfo i" +
            " join fetch i.interviewVideoList iv" +
            " join fetch iv.usedInterviewQuestion ui" +
            " where (i.memberConsultant.id = :memberId or i.memberStudent.id = :memberId) and i.id = :infoId")
    Optional<InterviewInfo> findInfoById(@Param("memberId") Long memberId, @Param("infoId") Long infoId);

    @Query("select i from InterviewInfo i" +
            " where i.memberStudent.id = :id")
    List<InterviewInfo> selectAllById(@Param("id") Long id);

}
