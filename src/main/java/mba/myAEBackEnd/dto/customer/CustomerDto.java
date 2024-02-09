package mba.myAEBackEnd.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String businessName;
    private String vatNumber;
    private String siren;
    private Set<CustomerAddressDto> addresses;
    private CustomerContactDto contact;
    private CustomerAddressDto firstAddress;
}
