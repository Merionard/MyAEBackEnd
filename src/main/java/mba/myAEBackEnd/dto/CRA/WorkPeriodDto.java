package mba.myAEBackEnd.dto.CRA;

import lombok.Data;
import lombok.experimental.Accessors;
import mba.myAEBackEnd.entity.WorkPeriodLine;

import java.util.Collection;
import java.util.HashSet;

@Data
@Accessors(chain = true)
public class WorkPeriodDto {
    private Long id;
    private int month;
    private int year;
    private Collection<WorkPeriodLineDto> lines = new HashSet<>();
    private Long userId;
}
