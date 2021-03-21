package Rservation.vacation.project.repository;

import Rservation.vacation.project.domain.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DateRepositoryImpl implements DateRepository{

    private final EntityManager em;
    @Override
    public void save(Date date) {
        em.persist(date);
    }

    @Override
    public List<Date> findById(Long id) {
        return em.createQuery("select d from  Date d where d.id=: id ", Date.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Date> findByAll() {
        return em.createQuery("select d from  Date d", Date.class).getResultList();
    }
}
