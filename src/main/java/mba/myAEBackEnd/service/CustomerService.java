package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CustomerDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.Customer;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.mapper.CustomerMapper;
import mba.myAEBackEnd.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private UserService userService;
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerDto createNewCustomer(CustomerDto customerDto, UserDto userDto) {
        Customer newCustomer = customerMapper.toEntityCustomer(customerDto);
        User user = userService.findUserByEmail(userDto.getEmail());
        newCustomer.setUser(user);
        customerRepository.save(newCustomer);
        return customerDto;
    }

    public List<CustomerDto> fetchAllCustomers(UserDto userDto) {
        User user = userService.findUserByEmail(userDto.getEmail());
        return customerRepository.findAllByUser(user).stream()
                .map(customer -> customerMapper.toCustomerDto(customer))
                .toList();
    }

    public CustomerDto fetchCustomerById(Long id) throws Exception {
        Customer customer = findOneById(id);
        return customerMapper.toCustomerDto(customer);
    }

    public CustomerDto updateCustomer(Long customerId,CustomerDto customerDto) throws Exception {
        Customer customer = findOneById(customerId);
        Customer updatedCustomer = customerMapper.toEntityCustomer(customerDto);
        updatedCustomer.setUser(customer.getUser());
        updatedCustomer = customerRepository.save(updatedCustomer);
        return customerMapper.toCustomerDto(updatedCustomer);

    }

    public Customer findOneById(Long id) throws Exception {
        return customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Pas de customer pour id: " + id));
    }

    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }
}
