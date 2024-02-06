package mba.myAEBackEnd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddressDto {
    private Long id;
    private String poCode;
    private String country;
    private String addressName;
    private int number;
    private String siret;
}
