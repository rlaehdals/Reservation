package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.Date;
import Rservation.vacation.project.repository.DateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DateServiceImpl implements DateService{

    private final DateRepository dateRepository;
    @Override
    public Long join(Date date) {
        dateRepository.save(date);
        return date.getId();
    }
    @Override
    public List<Date> findById(Long id) {
        return dateRepository.findById(id);
    }

    @Override
    public List<Date> findAll() {
        return dateRepository.findByAll();
    }
}

