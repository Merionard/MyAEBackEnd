package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDayRepository extends JpaRepository<WorkDay,Long> {
}
