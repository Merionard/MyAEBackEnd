package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.CustomerDto;
import mba.myAEBackEnd.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toCustomerDto(Customer customer);

    @Mapping(target = "user" , ignore = true)
    Customer toEntityCustomer (CustomerDto customerDto);
}
