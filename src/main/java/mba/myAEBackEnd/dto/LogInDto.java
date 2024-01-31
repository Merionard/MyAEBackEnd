package mba.myAEBackEnd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogInDto {

    private String userName;
    private String password;
}
