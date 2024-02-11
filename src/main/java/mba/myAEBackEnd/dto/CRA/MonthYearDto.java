package mba.myAEBackEnd.dto.CRA;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class MonthYearDto {

    private int month;
    private int year;
}
