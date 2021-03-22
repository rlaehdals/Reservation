package Rservation.vacation.project.repository;

import Rservation.vacation.project.domain.Date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateRepository extends JpaRepository<Date, Long> {
}
