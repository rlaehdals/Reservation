package Rservation.vacation.project.repository;

import Rservation.vacation.project.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository{

    private final EntityManager em;

    @Override
    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return em.find(Reservation.class, id);
    }

    @Override
    public List<Reservation> findByName(String name) {
        return em.createQuery("select r from Reservation r where r.name=:name", Reservation.class)
                .setParameter("name", name)
                .getResultList();
    }
    @Override
    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class).getResultList();
    }

    @Override
    public List<Reservation> findAllWithJoinFetch() {
        return em.createQuery(
                "select r from Reservation r" +
                " join fetch r.userInfo")
                .setFirstResult(0)
                .setMaxResults(100)
                .getResultList();
    }
}