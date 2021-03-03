package Rservation.vacation.project.repository.query;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationQueryRepository {

    private final EntityManager em;

    public List findReservationDto(){
        return em.createQuery(
                "select new Rservation.vacation.project.repository.query.reservationSimpleDtos(r.name,r.peopleCount,r.reservationTime)"+
                        " from Reservation r" +
                        " join r.userInfo ui"
        ).getResultList();
    }
}
