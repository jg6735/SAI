package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.job.dto.EnterpriseName;
import com.ssafy.sai.domain.job.dto.JobName;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemberSignUpRequest {

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}",
            message = "올바르지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotNull(message = "생년월일은 필수 입력값입니다.")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthday;

    private MemberStatus memberStatus;

    @NotNull(message = "기수 정보는 필수 입력값입니다.")
    private int year;

    @NotNull(message = "캠퍼스 정보는 필수 입력값입니다.")
    private CampusDto campus;

    @NotBlank(message = "연락처는 필수 입력값입니다.")
    private String phone;

    private List<JobName> interestedJobs;
    private List<EnterpriseName> interestedEnterprises;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .memberStatus(memberStatus)
                .year(year)
                .name(name)
                .birthday(birthday)
                .phone(phone)
                .build();
    }
}
