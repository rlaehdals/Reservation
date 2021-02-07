package Rservation.vacation.project.repository;

import Rservation.vacation.project.domain.Date;

import java.util.List;

public interface DateRepository {

    void save(Date date);

    List<Date> findById(Long id);
    List<Date> findByAll();

}
