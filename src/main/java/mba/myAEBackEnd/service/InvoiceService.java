package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.dto.UserDto;
import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import mba.myAEBackEnd.enums.InvoiceStatus;
import mba.myAEBackEnd.mapper.InvoiceMapper;
import mba.myAEBackEnd.repository.InvoiceLineRepository;
import mba.myAEBackEnd.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private InvoiceLineRepository invoiceLineRepository;
    private UserService userService;

    public InvoiceDto createInvoice(InvoiceDto invoiceDto,User user){

        Invoice newInvoice = invoiceMapper.toEntity(invoiceDto,user);
        newInvoice.setStatus(InvoiceStatus.DRAFT);
        invoiceRepository.save(newInvoice);
        return invoiceMapper.toDto(newInvoice);
    }

    public InvoiceDto updateInvoice(InvoiceDto invoiceDto,User user)  {


        Invoice updatedInvoice = invoiceMapper.toEntity(invoiceDto,user);
        invoiceLineRepository.deleteAllById(invoiceDto.getDeletedLines());
        invoiceRepository.save(updatedInvoice);

        return  invoiceMapper.toDto(updatedInvoice);
    }

    public void deleteInvoice(Long invoiceId){
        invoiceRepository.findById(invoiceId);
    }

    public List<InvoiceDto> fetchAllInvoiceByUser (UserDto userDto){
        User user =userService.findUserByEmail(userDto.getEmail());
        return invoiceRepository.getInvoicesByUser(user).stream()
                .map(invoice->invoiceMapper.toDto(invoice))
                .toList();
    }
}
