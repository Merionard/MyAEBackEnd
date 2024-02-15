package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {


    @Mapping(target = "user", source = "user")
    Invoice toEntity(InvoiceDto dto, User user);

    @AfterMapping
    default Invoice  complete(@MappingTarget Invoice invoice){
        invoice.getLines().forEach(l->l.setInvoice(invoice));
        return invoice;
    }

    @Mapping(target = "user", source = "user.userId")
    @Mapping(target = "deletedLines", ignore = true)
    InvoiceDto toDto(Invoice invoice);


}
