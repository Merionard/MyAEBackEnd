package mba.myAEBackEnd.dto.CRA;

import lombok.Data;
import lombok.experimental.Accessors;
import mba.myAEBackEnd.entity.WorkDay;

import java.util.Set;

@Data
@Accessors(chain = true)
public class WorkPeriodLineDto {
    private Long id;
    private Long customerId;
    private int nbDaysWorked;
    private Set<WorkDay> workDays;
}
