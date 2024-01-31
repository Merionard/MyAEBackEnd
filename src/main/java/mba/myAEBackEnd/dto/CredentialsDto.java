package mba.myAEBackEnd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialsDto {

    private String login;
    private char[] password;
}
