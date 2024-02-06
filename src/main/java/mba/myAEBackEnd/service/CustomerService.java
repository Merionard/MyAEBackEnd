package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CustomerDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.Customer;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.mapper.CustomerMapper;
import mba.myAEBackEnd.mapper.UserMapper;
import mba.myAEBackEnd.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private UserService userService;
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerDto createNewCustomer(CustomerDto customerDto, UserDto userDto){
        Customer newCustomer =  customerMapper.toEntityCustomer(customerDto);
        User user = userService.findUserByEmail(userDto.getEmail());
        newCustomer.setUser(user);
        customerRepository.save(newCustomer);
        return customerDto;
    }

    public List<CustomerDto> fetchAllCustomers(UserDto userDto){
        User user = userService.findUserByEmail(userDto.getEmail());
        return customerRepository.findAllByUser(user).stream()
                .map(customer -> customerMapper.toCustomerDto(customer))
                .collect(Collectors.toList());
    }
}
