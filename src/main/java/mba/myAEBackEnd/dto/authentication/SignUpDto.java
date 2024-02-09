package mba.myAEBackEnd.dto.authentication;

import lombok.Data;

@Data
public class SignUpDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
