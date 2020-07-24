package com.nextloop.nearlog.api.domain.poi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoiRepository extends JpaRepository<Poi, Long> {
    List<Poi> findAllByUserId(String email);
}
