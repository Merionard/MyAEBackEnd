package mba.myAEBackEnd.controllers;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class InvoiceController {


    private InvoiceService invoiceService;


    @PostMapping("/invoice")
    public ResponseEntity<InvoiceDto> newInvoice(@RequestBody InvoiceDto invoiceDto){
        InvoiceDto dto = invoiceService.createInvoice(invoiceDto);
        return ResponseEntity.ok(dto);

    }

    @PutMapping("/invoice")
    public ResponseEntity<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoiceDto){
        InvoiceDto dto = invoiceService.updateInvoice(invoiceDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/invoice/{id}")
    public void deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
    }

}
