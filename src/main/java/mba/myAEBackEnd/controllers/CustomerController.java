package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CustomerDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;


    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> newCustomer(@RequestBody @Validated CustomerDto customerDto, @AuthenticationPrincipal UserDto userDto) {

        customerService.createNewCustomer(customerDto, userDto);
        return ResponseEntity.ok(customerDto);
    }
}
