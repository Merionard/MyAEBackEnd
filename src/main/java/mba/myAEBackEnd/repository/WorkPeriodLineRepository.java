package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.WorkPeriodLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPeriodLineRepository extends JpaRepository<WorkPeriodLine,Long> {
}
