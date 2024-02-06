package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.CustomerDto;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;


    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> newCustomer(@RequestBody @Validated CustomerDto customerDto, @AuthenticationPrincipal UserDto userDto) {

        customerService.createNewCustomer(customerDto, userDto);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomer(@AuthenticationPrincipal UserDto userDto) {
        List<CustomerDto> customers = customerService.fetchAllCustomers(userDto);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) throws Exception {
        CustomerDto customer = customerService.fetchCustomerById(id);
        return ResponseEntity.ok(customer);

    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Validated CustomerDto customerDto, @PathVariable Long customerId) throws Exception {
        CustomerDto customer = customerService.updateCustomer(customerId, customerDto);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.ok("Client supprimé avec succès");
    }


}
