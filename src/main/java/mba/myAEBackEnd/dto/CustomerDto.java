package mba.myAEBackEnd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class CustomerDto {

    private String businessName;
    private String vatNumber;
    private String siren;
    private Set<CustomerAddressDto> addresses;
    private Set<CustomerContactDto> contacts;
}
