package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CustomerDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.entity.Customer;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.mapper.CustomerMapper;
import mba.myAEBackEnd.repository.CustomerRepsitory;
import mba.myAEBackEnd.repository.UserRepository;
import mba.myAEBackEnd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class CustomerController {

    private UserService userService;
    private CustomerMapper customerMapper;
    private CustomerRepsitory customerRepsitory;


    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> newCustomer(@RequestBody @Validated CustomerDto customerDto , @AuthenticationPrincipal UserDto userDto){
        Customer newCustomer =  customerMapper.toEntityCustomer(customerDto);
       User user = userService.findUserByEmail(userDto.getEmail());
       newCustomer.setUser(user);
       customerRepsitory.save(newCustomer);

        return ResponseEntity.ok(customerDto);
    }
}
