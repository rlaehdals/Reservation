package Rservation.vacation.project.repository.query;



import Rservation.vacation.project.domain.QReservation;
import Rservation.vacation.project.domain.QReservationDto;
import Rservation.vacation.project.domain.QUserInfo;
import Rservation.vacation.project.domain.ReservationDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static Rservation.vacation.project.domain.QReservation.*;
import static Rservation.vacation.project.domain.QUserInfo.*;

@Repository
@RequiredArgsConstructor
public class ReservationQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public List findReservationDto(){
        //querydsl 적용전
//        return em.createQuery(
//                "select new Rservation.vacation.project.repository.query.reservationSimpleDtos(r.userInfo.name,r.peopleCount,r.reservationTime)"+
//                        " from Reservation r" +
//                        " join r.userInfo ui"
//        ).getResultList();
        //querydsl 적용후
        List<ReservationDto> result = queryFactory
                .select(new QReservationDto(
                        reservation.name,
                        reservation.peopleCount,
                        reservation.reservationTime))
                .from(reservation)
                .join(reservation.userInfo, userInfo).fetchJoin()
                .fetch();
        return result;
    }

}
