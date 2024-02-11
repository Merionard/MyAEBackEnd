package mba.myAEBackEnd.dto.CRA;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class WorkPeriodLineDto {
    private Long id;
    private Long customerId;
    private int nbDaysWorked;
    private Set<WorkDayDto> workDays;
    private Set<Long> workDaysToDelete = new HashSet<>();
}
