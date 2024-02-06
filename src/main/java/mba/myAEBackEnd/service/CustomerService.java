package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CustomerDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.Customer;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.mapper.CustomerMapper;
import mba.myAEBackEnd.repository.CustomerRepsitory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private UserService userService;
    private CustomerRepsitory customerRepsitory;
    private CustomerMapper customerMapper;

    public CustomerDto createNewCustomer(CustomerDto customerDto, UserDto userDto){
        Customer newCustomer =  customerMapper.toEntityCustomer(customerDto);
        User user = userService.findUserByEmail(userDto.getEmail());
        newCustomer.setUser(user);
        customerRepsitory.save(newCustomer);
        return customerDto;
    }
}
