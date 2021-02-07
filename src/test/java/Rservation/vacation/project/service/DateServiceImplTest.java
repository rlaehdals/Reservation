package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.Date;
import Rservation.vacation.project.repository.DateRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DateServiceImplTest {

    @Autowired DateRepository dateRepository;
    @Autowired DateService dateService;
    @Test
    void join() {
        Date date = new Date();
        dateService.join(date);

        List<Date> findId = dateRepository.findById(date.getId());

        Assertions.assertThat(date.getId()).isEqualTo(findId.stream().findAny().get().getId());
    }
}