package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.entity.WorkPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkPeriodRepository extends JpaRepository<WorkPeriod,Long> {

    Optional<WorkPeriod> findByMonthEqualsAndYearEqualsAndUser(int month, int year, User user);
}
