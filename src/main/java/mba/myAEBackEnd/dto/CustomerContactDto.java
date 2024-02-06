package mba.myAEBackEnd.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerContactDto {
    private Long id;
    private String name;
    private String firstName;
    private String email;
}
