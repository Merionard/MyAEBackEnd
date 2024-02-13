package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.service.InvoiceService;
import mba.myAEBackEnd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InvoiceController {


    private InvoiceService invoiceService;
    private UserService userService;


    @GetMapping("/invoice")
    public ResponseEntity<List<InvoiceDto>> getAllInvoiceByUser(@AuthenticationPrincipal UserDto userDto){
        List<InvoiceDto> dto = invoiceService.fetchAllInvoiceByUser(userDto);
        return  ResponseEntity.ok(dto);
    }

    @PostMapping("/invoice")
    public ResponseEntity<InvoiceDto> newInvoice(@RequestBody InvoiceDto invoiceDto,@AuthenticationPrincipal UserDto userDto){
        InvoiceDto dto = invoiceService.createInvoice(invoiceDto,userService.findUserByEmail(userDto.getEmail()));
        return ResponseEntity.ok(dto);

    }

    @PutMapping("/invoice")
    public ResponseEntity<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoiceDto,@AuthenticationPrincipal UserDto userDto){
        InvoiceDto dto = invoiceService.updateInvoice(invoiceDto,userService.findUserByEmail(userDto.getEmail()));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/invoice/{id}")
    public void deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
    }

}
