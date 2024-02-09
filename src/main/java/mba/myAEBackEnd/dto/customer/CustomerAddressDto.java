package mba.myAEBackEnd.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddressDto {
    private Long id;
    private String poCode;
    private String country;
    private String addressName;
    private String number;
    private String siret;
}
