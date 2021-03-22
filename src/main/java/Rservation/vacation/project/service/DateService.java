package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.Date;

import java.util.List;
import java.util.Optional;

public interface DateService {

    Long join(Date date);

    Optional<Date> findById(Long id);
    List<Date> findAll();
}
