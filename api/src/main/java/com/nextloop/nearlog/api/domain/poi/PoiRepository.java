package com.nextloop.nearlog.api.domain.poi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PoiRepository extends JpaRepository<Poi, Long> {

    List<Poi> findAllByUserId(String email);
    List<Poi> findAllByCreatedDateAfter(LocalDateTime fiveDaysBefore);
    List<Poi> findAllByUserIdAndCreatedDateAfter(String email, LocalDateTime fiveDaysBefore);
}
