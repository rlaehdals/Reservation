package Rservation.vacation.project.service;
import Rservation.vacation.project.domain.Date;
import Rservation.vacation.project.repository.DateRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<Date> findDate = dateRepository.findById(dateId);
        Assertions.assertThat(dateId).isEqualTo(findDate.stream().findAny().get().getId());

    }
}