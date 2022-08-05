package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.member.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
