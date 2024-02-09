package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.customer.CustomerAddressDto;
import mba.myAEBackEnd.dto.customer.CustomerDto;
import mba.myAEBackEnd.entity.Customer;
import mba.myAEBackEnd.entity.CustomerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toCustomerDto(Customer customer);

    @Mapping(target = "user" , ignore = true)
    @Mapping(target = "addresses", source="firstAddress" ,qualifiedByName = "firstAddress")
    Customer toEntityCustomer (CustomerDto customerDto);

    @Named("firstAddress")
    public default Set<CustomerAddress> mapFirstAddress(CustomerAddressDto firstAddress){
    Set<CustomerAddress> addresses = new HashSet<>();
    CustomerAddress newAddress = toAddress(firstAddress);
    addresses.add(newAddress);
    return addresses;

    }

    CustomerAddress toAddress (CustomerAddressDto customerAddressDto);
}
