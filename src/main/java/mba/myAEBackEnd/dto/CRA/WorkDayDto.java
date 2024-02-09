package mba.myAEBackEnd.dto.CRA;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class WorkDayDto {
    private Long id;
    private LocalDate date;
    private float duration;
}
