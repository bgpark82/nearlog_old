package com.nextloop.nearlog.api.domain.poi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
//@EnableJpaAuditing            // 해당 설정을 없애야 데이터베이스 입력하기 전에 날짜를 자동적으로 안들어가도록 만들 수 있다
class PoiRepositoryTest {

    @Autowired
    PoiRepository repository;

    @BeforeEach
    void setUp() {
        Poi poi1 = Poi.builder().id(1L).userId("bgpark82@gmail.com").build();
        poi1.setCreatedDate(LocalDateTime.now().minusDays(7));
        Poi poi2 = Poi.builder().id(2L).userId("bgpark82@gmail.com").build();
        poi2.setCreatedDate(LocalDateTime.now());
        Poi poi3 = Poi.builder().id(3L).userId("kassjd@gmail.com").build();
        poi3.setCreatedDate(LocalDateTime.now());
        repository.save(poi1);
        repository.save(poi2);
        repository.save(poi3);
    }

    @Test
    void find_all_test() {
        Poi poi = Poi.builder().id(1L).build();
        poi.setCreatedDate(LocalDateTime.now().minusDays(4));
        poi.setModifiedDate(LocalDateTime.now().minusDays(4));

        Poi save = repository.save(poi);

        assertThat(save.getModifiedDate()).isNotNull();
    }

    @Test
    void find_all_five_days_before() {
        List<Poi> newPoi = repository.findAllByCreatedDateAfter(
                LocalDateTime.now().minusDays(5));

        assertThat(newPoi.size()).isEqualTo(2);
    }

    @Test
    void find_all_five_days_before_with_user_id() {
        List<Poi> newPoi = repository.findAllByUserIdAndCreatedDateAfter(
                "bgpark82@gmail.com",
                LocalDateTime.now().minusDays(5));

        assertThat(newPoi.size()).isEqualTo(1);
    }
}