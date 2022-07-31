package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.member.domain.Campus;
import lombok.Data;

@Data
public class CampusDto {
    private String city;
    private Integer classNumber;

    public CampusDto(String city, Integer classNumber) {
        this.city = city;
        this.classNumber = classNumber;
    }

    public Campus toEntity() {
        return Campus.builder()
                .city(city)
                .classNumber(classNumber)
                .build();
    }
}