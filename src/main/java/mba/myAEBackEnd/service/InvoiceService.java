package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.enums.InvoiceStatus;
import mba.myAEBackEnd.exception.BusinessException;
import mba.myAEBackEnd.mapper.InvoiceMapper;
import mba.myAEBackEnd.repository.InvoiceLineRepository;
import mba.myAEBackEnd.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private InvoiceLineRepository invoiceLineRepository;

    public InvoiceDto createInvoice(InvoiceDto invoiceDto){

        Invoice newInvoice = invoiceMapper.toEntity(invoiceDto);
        newInvoice.setStatus(InvoiceStatus.DRAFT);
        invoiceRepository.save(newInvoice);
        return invoiceMapper.toDto(newInvoice);
    }

    public InvoiceDto updateInvoice(InvoiceDto invoiceDto)  {


        Invoice updatedInvoice = invoiceMapper.toEntity(invoiceDto);
        invoiceLineRepository.deleteAllById(invoiceDto.getLinesToDelete());
        invoiceRepository.save(updatedInvoice);

        return  invoiceMapper.toDto(updatedInvoice);
    }

    public void deleteInvoice(Long invoiceId){
        invoiceRepository.findById(invoiceId);
    }
}
