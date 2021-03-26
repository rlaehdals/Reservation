package Rservation.vacation.project.repository;

import Rservation.vacation.project.domain.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static Rservation.vacation.project.domain.QReservation.*;
import static Rservation.vacation.project.domain.QUserInfo.*;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Reservation> findAllWithJoinFetch(Pageable pageable) {
        List<Reservation> result = queryFactory
                .select(reservation)
                .from(reservation)
                .join(reservation.userInfo, userInfo).fetchJoin()
                .offset(pageable.getOffset())
                .fetch();
        return result;
    }
}
