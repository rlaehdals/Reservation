package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.Date;

import java.util.List;

public interface DateService {

    Long join(Date date);

    List<Date> findById(Long id);
    List<Date> findAll();
}
