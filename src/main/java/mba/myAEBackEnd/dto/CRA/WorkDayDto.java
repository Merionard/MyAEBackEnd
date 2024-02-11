package mba.myAEBackEnd.dto.CRA;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
public class WorkDayDto {
    private Long id;
    private ZonedDateTime date;
    private float duration;
}
