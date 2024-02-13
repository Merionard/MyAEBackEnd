package mba.myAEBackEnd.mapper;

import mba.myAEBackEnd.dto.invoice.InvoiceDto;
import mba.myAEBackEnd.entity.Invoice;
import mba.myAEBackEnd.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {


    @Mapping(target = "user", source = "user")
    Invoice toEntity(InvoiceDto dto, User user);

    @Mapping(target = "user", source = "user.userId")
    @Mapping(target = "deletedLines", ignore = true)
    InvoiceDto toDto(Invoice invoice);


}
