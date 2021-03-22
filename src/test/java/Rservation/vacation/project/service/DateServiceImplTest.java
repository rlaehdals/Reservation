package Rservation.vacation.project.service;
import Rservation.vacation.project.domain.Date;
import Rservation.vacation.project.repository.DateRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DateServiceImplTest {
    @Autowired DateService dateService;
    @Autowired DateRepository dateRepository;


    @Test
    void 확인(){
        Date date = Date.createDate("1", "1", "1", "1", "1", "1");
        Long dateId = dateService.join(date);
        Optional<Date> findDate = dateRepository.findById(date.getId());
        Assertions.assertThat(dateId).isEqualTo(findDate.stream().findAny().get().getId());
    }
}